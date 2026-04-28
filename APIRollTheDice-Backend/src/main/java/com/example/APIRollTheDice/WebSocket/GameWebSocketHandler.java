package com.example.APIRollTheDice.WebSocket;

import com.example.APIRollTheDice.Enum.WSMessageTypes;
import com.example.APIRollTheDice.Enum.WSScopeEnum;
import com.example.APIRollTheDice.Model.Obj.Agenda.Agenda;
import com.example.APIRollTheDice.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.Model.Obj.Chat.ChatMessage;
import com.example.APIRollTheDice.Model.Obj.Conversation.Conversation;
import com.example.APIRollTheDice.Model.Obj.Game.Game;
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
import com.example.APIRollTheDice.WebSocket.Object.WSMessage;
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

    private final TokenService tokenService;
    private final UserService userService;
    private final ChatService chatService;
    private final GameService gameService;
    private final AgendaService agendaService;
    private final MessageService messageService;
    private final ConversationService conversationService;

    public GameWebSocketHandler(TokenService tokenService, UserService userService, ChatService chatService,
                                GameService gameService, AgendaService agendaService, MessageService messageService, ConversationService conversationService) {
        this.conversationService = conversationService;
        this.messageService = messageService;
        this.agendaService = agendaService;
        this.userService = userService;
        this.tokenService = tokenService;
        this.chatService = chatService;
        this.gameService = gameService;
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
                handleGame(msg);
                break;
            case CHAT:
                handleChat(msg);
                break;
            case AGENDA:
                handleAgenda(msg);
                break;
            case PRIVATE:
                handlePrivate(msg);
                break;

            case NOTIFICATION:
                handleNotification(msg);
                break;
            case TOKEN:
                handleToken(msg);
                break;
        }
    }

    private void handleChat(WSMessage msg) throws Exception {

        Map<String, Object> payload = msg.getPayload();
        if (payload == null) return;

        switch (msg.getType()) {

            case CHAT_MESSAGE:

                ChatMessage chatMessage = getFromPayload(msg, "chatMessage", ChatMessage.class);

                if (chatMessage == null) return;

                chatMessage = chatService.CreateChatMessage(chatMessage);

                msg.setPayload(Map.of("chatMessage", chatMessage));

                sendToRoom(msg.getGameId(), msg);
                break;
            case UPDATED_MESSAGE:
                ChatMessage updatedMessage = getFromPayload(msg, "chatMessage", ChatMessage.class);

                if (updatedMessage == null) return;

                chatMessage = chatService.UpdateChatMessage(updatedMessage);

                msg.setPayload(Map.of("chatMessage", chatMessage));
                sendToRoom(msg.getGameId(), msg);
                break;
            case DELETED_MESSAGE:
                ChatMessage deletedMessage = getFromPayload(msg, "chatMessage", ChatMessage.class);
                if (deletedMessage == null) return;

                chatService.DeleteChatMessage(deletedMessage.getId());

                msg.setPayload(Map.of("chatMessage", deletedMessage));
                sendToRoom(msg.getGameId(), msg);
                break;

            case CREATE_CHANNEL:

                ChatChanel chatChanel = getFromPayload(msg, "chatChanel", ChatChanel.class);

                if (chatChanel == null) return;

                chatChanel = chatService.CreateChatChanelle(chatChanel);

                msg.setPayload(Map.of("chatChanel", chatChanel));

                sendToRoom(msg.getGameId(), msg);
                break;
            case UPDATE_CHANNEL:
                ChatChanel updatedChanel = getFromPayload(msg, "chatChanel", ChatChanel.class);

                if (updatedChanel == null) return;

                chatChanel = chatService.UpdateChatChanelle(updatedChanel);

                msg.setPayload(Map.of("chatChanel", chatChanel));

                sendToRoom(msg.getGameId(), msg);
                break;
            case DELETE_CHANNEL:
                ChatChanel deletedChanel = getFromPayload(msg, "chatChanel", ChatChanel.class);
                if (deletedChanel == null) return;

                chatService.DeleteChatChannel(deletedChanel.getId());

                msg.setPayload(Map.of("chatChanel", deletedChanel));

                sendToRoom(msg.getGameId(), msg);
                break;
        }
    }

    private void handleToken(WSMessage msg) throws Exception {

        Map<String, Object> payload = msg.getPayload();
        if (payload == null) return;
        TokenPlaced tokenPlaced = new TokenPlaced();

        switch (msg.getType()) {

            case TOKEN_MOVE:
                tokenPlaced = getFromPayload(msg, "tokenPlaced", TokenPlaced.class);
                tokenPlaced = tokenService.UpdateTokenPlaced(tokenPlaced);
                msg.setPayload(Map.of("tokenPlaced", tokenPlaced));
                break;

            case TOKEN_PLACE:
                tokenPlaced = getFromPayload(msg, "tokenPlaced", TokenPlaced.class);
                tokenPlaced = tokenService.CreateTokenPlaced(tokenPlaced);
                msg.setPayload(Map.of("tokenPlaced", tokenPlaced));
                break;

            case TOKEN_REMOVE:

                Long idToken = getFromPayload(msg, "tokenId", Long.class);
                tokenService.DeleteTokenPlaced(idToken);
                msg.setPayload(Map.of("tokenId", idToken));
                break;
        }


        sendToRoom(msg.getGameId(), msg);
    }

    private void handleGame(WSMessage msg) throws Exception {
        switch (msg.getType()) {
            case CREATED_GAME:
                Game game = getFromPayload(msg, "game", Game.class);
                if (game == null) return;
                game = gameService.CreateGame(game);
                msg.setPayload(Map.of("game", game));
                sendToRoom(msg.getGameId(), msg);
                break;
            case DELETED_GAME:
                Long gameId = getFromPayload(msg, "gameId", Long.class);
                if (gameId == null) return;
                gameService.DeleteGame(gameId);
                msg.setPayload(Map.of("gameId", gameId));
                sendToRoom(msg.getGameId(), msg);
                break;
            case UPDATED_GAME:
                Game updatedGame = getFromPayload(msg, "game", Game.class);
                if (updatedGame == null) return;
                updatedGame = gameService.UpdateGame(updatedGame);
                msg.setPayload(Map.of("game", updatedGame));
                sendToRoom(msg.getGameId(), msg);
                break;

        }
    }

    private void handleAgenda(WSMessage msg) throws Exception {
        switch (msg.getType()) {
            case AGENDA_CREATE:
                Agenda agenda = getFromPayload(msg, "agenda", Agenda.class);
                if (agenda == null) return;
                agenda = agendaService.CreateAgenda(agenda);
                msg.setPayload(Map.of("agenda", agenda));
                sendToRoom(msg.getGameId(), msg);
                break;
            case AGENDA_UPDATE:
                Agenda updatedAgenda = getFromPayload(msg, "agenda", Agenda.class);
                if (updatedAgenda == null) return;
                updatedAgenda = agendaService.UpdateAgenda(updatedAgenda);
                msg.setPayload(Map.of("agenda", updatedAgenda));
                sendToRoom(msg.getGameId(), msg);
                break;
            case AGENDA_DELETE:
                Long agendaId = getFromPayload(msg, "agendaId", Long.class);
                if (agendaId == null) return;
                agendaService.DeleteAgenda(agendaId);
                msg.setPayload(Map.of("agendaId", agendaId));
                sendToRoom(msg.getGameId(), msg);
                break;

        }
    }

    private void handlePrivate(WSMessage msg) throws Exception {
        switch (msg.getType()) {
            case PRIVATE_MESSAGE:
                Long targetUserId = msg.getTargetUserId();
                Long senderUserId = msg.getSenderUserId();
                String content = msg.getContent();
                Conversation conversation = conversationService.findOrCreateConversation(senderUserId, targetUserId);
                Message privateMessage = new Message();
                privateMessage.setContent(content);
                privateMessage.setSender(userService.getUserById(senderUserId));
                privateMessage.setSentAt(LocalDateTime.now());
                privateMessage.setConversation(conversation);
                privateMessage.setModified(false);
                privateMessage.setRead(false);
                privateMessage = messageService.createMessage(privateMessage);

                for (User participant : conversation.getParticipants()) {
                    sendToUser(participant.getId(), msg);
                }
                break;
            case UPDATE_PRIVATE_MESSAGE:
                Long messageId = getFromPayload(msg, "messageId", Long.class);
                String newContent = getFromPayload(msg, "content", String.class);

                if (messageId == null || newContent == null) return;

                Message existingMessage = messageService.getMessageById(messageId);

                if (!existingMessage.getSender().getId().equals(msg.getSenderUserId())) {
                    return;
                }
                existingMessage.setContent(newContent);
                existingMessage.setModified(true);

                Message updatedMessage = messageService.updateMessage(existingMessage);

                msg.setPayload(Map.of("message", updatedMessage));

                for (User participant : updatedMessage.getConversation().getParticipants()) {
                    sendToUser(participant.getId(), msg);
                }
                break;
            case DELETE_PRIVATE_MESSAGE:
                Long messageId1 = getFromPayload(msg, "messageId", Long.class);
                if (messageId1 == null) return;

                Message existingMessage1 = messageService.getMessageById(messageId1);

                if (!existingMessage1.getSender().getId().equals(msg.getSenderUserId())) {
                    return;
                }

                Conversation conversation1 = existingMessage1.getConversation();

                messageService.deleteMessage(messageId1);

                msg.setPayload(Map.of("messageId", messageId1));

                for (User participant : conversation1.getParticipants()) {
                    sendToUser(participant.getId(), msg);
                }
                break;
            case READ_PRIVATE_MESSAGE:
                Long conversationId = getFromPayload(msg, "conversationId", Long.class);
                Long lastMessageId = getFromPayload(msg, "lastMessageId", Long.class);
                Long userId = msg.getSenderUserId();

                conversationService.updateLastRead(conversationId, userId, lastMessageId);

                msg.setPayload(Map.of(
                        "conversationId", conversationId,
                        "userId", userId,
                        "lastReadMessageId", lastMessageId
                ));

                Conversation conversationReturn = conversationService.getConversationById(conversationId);

                for (User participant : conversationReturn.getParticipants()) {
                    if (!participant.getId().equals(userId)) {
                        sendToUser(participant.getId(), msg);
                    }
                }
                break;
        }
    }

    private void handleNotification(WSMessage msg) throws Exception {
        switch (msg.getType()) {
            case NOTIFICATION:
                break;
        }
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

    private <T> T getFromPayload(WSMessage msg, String key, Class<T> clazz) {
        if (msg.getPayload() == null) return null;

        Object value = msg.getPayload().get(key);
        if (value == null) return null;

        return objectMapper.convertValue(value, clazz);
    }
}
