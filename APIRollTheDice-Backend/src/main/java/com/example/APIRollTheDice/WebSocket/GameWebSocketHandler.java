package com.example.APIRollTheDice.WebSocket;

import com.example.APIRollTheDice.Enum.WSMessageTypes;
import com.example.APIRollTheDice.Enum.WSScopeEnum;
import com.example.APIRollTheDice.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.Model.Obj.Chat.ChatMessage;
import com.example.APIRollTheDice.Model.Obj.Game.Token.TokenPlaced;
import com.example.APIRollTheDice.Model.Obj.User.User;
import com.example.APIRollTheDice.Service.Chat.ChatService;
import com.example.APIRollTheDice.Service.Game.Token.TokenService;
import com.example.APIRollTheDice.Service.User.UserService;
import com.example.APIRollTheDice.WebSocket.Object.GameRoom;
import com.example.APIRollTheDice.WebSocket.Object.WSMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Map<Long, GameRoom> rooms = new ConcurrentHashMap<>();

    private final Map<String, Long> sessionGameMap = new ConcurrentHashMap<>();

    private final Map<String, Long> sessionUserMap = new ConcurrentHashMap<>();

    private final TokenService tokenService  ;
    private final UserService userService;
    private final ChatService chatService;

    public GameWebSocketHandler(TokenService tokenService, UserService userService, ChatService chatService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.chatService = chatService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        Long gameId = getGameId(session);
        Long userId = getUserId(session);

        sessionGameMap.put(session.getId(), gameId);
        sessionUserMap.put(session.getId(), userId);

        GameRoom room = rooms.computeIfAbsent(gameId, GameRoom::new);
        room.addPlayer(session);

        WSMessage wsMessage = new WSMessage();
        wsMessage.setType(WSMessageTypes.PLAYER_JOINED);
        wsMessage.setScope(WSScopeEnum.GAME);
        wsMessage.setGameId(gameId);
        wsMessage.setSenderUserId(userId);

        User user = userService.getUserById(userId);
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", user.getUsername());
        payload.put("joinedAt", Instant.now());
        wsMessage.setPayload(payload);

        sendToRoom(gameId, wsMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {

        Long gameId = sessionGameMap.remove(session.getId());
        Long userId = sessionUserMap.remove(session.getId());

        GameRoom room = rooms.get(gameId);
        if (room != null) {
            room.removePlayer(session);
        }

        WSMessage wsMessage = new WSMessage();
        wsMessage.setType(WSMessageTypes.PLAYER_LEFT);
        wsMessage.setScope(WSScopeEnum.GAME);
        wsMessage.setGameId(gameId);
        wsMessage.setSenderUserId(userId);

        try {
            sendToRoom(gameId, wsMessage);
        } catch (Exception e) {}
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        WSMessage msg = objectMapper.readValue(message.getPayload(), WSMessage.class);

        switch (msg.getScope()) {
            case GAME :
               break;
            case CHAT :
                handleChat(msg);
                break;
            case AGENDA :
                break;
            case PRIVATE  :
                break;
            case SYSTEM :
                break;
            case NOTIFICATION :
                break;
            case TOKEN  :
                handleToken(msg);
                break;
        }
    }

    private void handleChat(WSMessage msg) throws Exception {
         switch (msg.getType()) {
            case CHAT_MESSAGE :
                ChatMessage chatMessage = msg.getPayload().get("chatMessage") != null ? objectMapper.convertValue(msg.getPayload().get("chatMessage"), ChatMessage.class) : null;
                chatMessage = chatService.CreateChatMessage(chatMessage);
                msg.setPayload(Map.of("chatMessage", chatMessage));
                sendToRoom(msg.getGameId(), msg);
                break;
            case CREATE_CHANNEL  :
                ChatChanel chatChanel = msg.getPayload().get("chatChanel") != null ? objectMapper.convertValue(msg.getPayload().get("chatChanel"), ChatChanel.class) : null;
                chatChanel = chatService.CreateChatChanelle(chatChanel);
                msg.setPayload(Map.of("chatChanel", chatChanel));
                 break;
         }
    }

    private void handleToken(WSMessage msg) throws Exception {
        TokenPlaced tokenPlaced = msg.getPayload().get("tokenPlaced") != null ? objectMapper.convertValue(msg.getPayload().get("tokenPlaced"), TokenPlaced.class) : null;
        switch (msg.getType()) {
            case TOKEN_MOVE :
                tokenPlaced= tokenService.UpdateTokenPlaced(tokenPlaced);
                msg.setPayload(Map.of("tokenPlaced", tokenPlaced));
                break;
            case TOKEN_PLACE :
                tokenPlaced= tokenService.CreateTokenPlaced(tokenPlaced);
                msg.setPayload(Map.of("tokenPlaced", tokenPlaced));
                break;
            case TOKEN_REMOVE :
                tokenService.DeleteTokenPlaced(tokenPlaced.getId());

                break;
        }
        sendToRoom(msg.getGameId(), msg);
    }



    private void sendToRoom(Long gameId, WSMessage msg) throws Exception {
        GameRoom room = rooms.get(gameId);
        if (room == null) return;

        room.broadcast(objectMapper.writeValueAsString(msg));
    }

    private Long getGameId(WebSocketSession session) {
        return Long.parseLong(getQueryParam(session, "gameId"));
    }

    private Long getPlayerId(WebSocketSession session) {
        return Long.parseLong(getQueryParam(session, "playerId"));
    }
    private Long getUserId(WebSocketSession session) {
        return Long.parseLong(getQueryParam(session, "senderUserId"));
    }

    private String getQueryParam(WebSocketSession session, String key) {
        String query = session.getUri().getQuery();

        if (query == null) return null;

        return Arrays.stream(query.split("&"))
                .map(param -> param.split("="))
                .filter(pair -> pair[0].equals(key))
                .map(pair -> pair[1])
                .findFirst()
                .orElse(null);
    }
}
