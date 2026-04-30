package com.example.APIRollTheDice.WebSocket;

import com.example.APIRollTheDice.WebSocket.Enum.WSMessageTypes;
import com.example.APIRollTheDice.WebSocket.Enum.WSScopeEnum;
import com.example.APIRollTheDice.Model.Obj.Agenda.Agenda;
import com.example.APIRollTheDice.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.Model.Obj.Chat.ChatMessage;
import com.example.APIRollTheDice.Model.Obj.Conversation.Conversation;
import com.example.APIRollTheDice.Model.Obj.Game.Token.TokenPlaced;
import com.example.APIRollTheDice.Model.Obj.Conversation.Message;
import com.example.APIRollTheDice.Model.Obj.User.User;
import com.example.APIRollTheDice.Service.Agenda.AgendaService;
import com.example.APIRollTheDice.Service.Chat.ChatService;
import com.example.APIRollTheDice.Service.ConversationService;
import com.example.APIRollTheDice.Service.Game.GameService;
import com.example.APIRollTheDice.Service.Game.Token.TokenService;
import com.example.APIRollTheDice.Service.MessageService;
import com.example.APIRollTheDice.Service.User.UserService;
import com.example.APIRollTheDice.WebSocket.Object.GameRoom;
import com.example.APIRollTheDice.WebSocket.DTO.WSMessage;
import com.example.APIRollTheDice.WebSocket.Service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
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


    private final UserService userService;
    private final GameWSService gameWSService;
    private final AgendaWSService agendaWSService;
    private final ChatWSService chatWSService;
    private final NotificationWSService notificationWSService;
    private final PrivateMessageWSService privateMessageWSService;
    private final TokenWSService tokenWSService;

    public GameWebSocketHandler(UserService userService, GameWSService gameWSService, AgendaWSService agendaWSService, ChatWSService chatWSService, NotificationWSService notificationWSService, PrivateMessageWSService privateMessageWSService, TokenWSService tokenWSService) {
        this.userService = userService;
        this.gameWSService = gameWSService;
        this.agendaWSService = agendaWSService;
        this.chatWSService = chatWSService;
        this.notificationWSService = notificationWSService;
        this.privateMessageWSService = privateMessageWSService;
        this.tokenWSService = tokenWSService;
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
        } catch (Exception e) {
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        WSMessage msg = objectMapper.readValue(message.getPayload(), WSMessage.class);

        switch (msg.getScope()) {
            case GAME:
                gameWSService.handle(msg);
                break;
            case CHAT:
                chatWSService.handle(msg);
                break;
            case AGENDA:
                agendaWSService.handle(msg);
                break;
            case PRIVATE:
                privateMessageWSService.handle(msg);
                break;
            case NOTIFICATION:
                notificationWSService.handle(msg);
                break;
            case TOKEN:
                tokenWSService.handle(msg);
                break;
        }
    }
    
    public void OutsiderSendToRoom(Long gameId, WSMessage msg) throws Exception {
        sendToRoom(gameId, msg);
    }

    private void sendToRoom(Long gameId, WSMessage msg) throws Exception {
        GameRoom room = rooms.get(gameId);
        if (room == null) return;

        room.broadcast(objectMapper.writeValueAsString(msg));
    }

    public void OutsiderSendToUser(Long userId, WSMessage msg) throws Exception {
        sendToUser(userId, msg);
    }

    private void sendToUser(Long userId, WSMessage msg) throws Exception {

        String json = objectMapper.writeValueAsString(msg);

        for (Map.Entry<String, Long> entry : sessionUserMap.entrySet()) {

            if (entry.getValue().equals(userId)) {

                String sessionId = entry.getKey();

                for (GameRoom room : rooms.values()) {

                    WebSocketSession session = room.getSession(sessionId);

                    if (session != null && session.isOpen()) {
                        session.sendMessage(new TextMessage(json));
                    }
                }
            }
        }
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
