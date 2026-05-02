package com.example.APIRollTheDice.WebSocket;

import com.example.APIRollTheDice.WebSocket.Core.WSSender;
import com.example.APIRollTheDice.WebSocket.Core.WSSessionRegistry;
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
    private final WSSessionRegistry registry;
    private final WSSender sender;



    private final UserService userService;
    private final GameWSService gameWSService;
    private final AgendaWSService agendaWSService;
    private final ChatWSService chatWSService;
    private final NotificationWSService notificationWSService;
    private final PrivateMessageWSService privateMessageWSService;
    private final TokenWSService tokenWSService;

    public GameWebSocketHandler(UserService userService, GameWSService gameWSService, AgendaWSService agendaWSService, ChatWSService chatWSService,
                                NotificationWSService notificationWSService, PrivateMessageWSService privateMessageWSService,
                                TokenWSService tokenWSService, WSSessionRegistry registry, WSSender sender) {
        this.userService = userService;
        this.gameWSService = gameWSService;
        this.agendaWSService = agendaWSService;
        this.chatWSService = chatWSService;
        this.notificationWSService = notificationWSService;
        this.privateMessageWSService = privateMessageWSService;
        this.tokenWSService = tokenWSService;
        this.registry = registry;
        this.sender = sender;
    }




    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        Long gameId = getGameId(session);
        Long userId = getUserId(session);
        registry.registerSession(session, gameId, userId);

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

        sender.sendToRoom(gameId, wsMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {

        Long gameId = getGameId(session);
        Long userId = getUserId(session);

        registry.removeSession(session);

        WSMessage wsMessage = new WSMessage();
        wsMessage.setType(WSMessageTypes.PLAYER_LEFT);
        wsMessage.setScope(WSScopeEnum.GAME);
        wsMessage.setGameId(gameId);
        wsMessage.setSenderUserId(userId);

        try {
            sender.sendToRoom(gameId, wsMessage);
        } catch (Exception e) {}
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
