package com.example.APIRollTheDice.WebSocket;

import com.example.APIRollTheDice.Enum.WSMessageTypes;
import com.example.APIRollTheDice.Model.Obj.Game.Token.TokenPlaced;
import com.example.APIRollTheDice.Service.Game.Token.TokenService;
import com.example.APIRollTheDice.WebSocket.Object.GameRoom;
import com.example.APIRollTheDice.WebSocket.Object.WSMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GameWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Map<Long, GameRoom> rooms = new ConcurrentHashMap<>();

    private final Map<String, Long> sessionGameMap = new ConcurrentHashMap<>();

    private final Map<String, Long> sessionPlayerMap = new ConcurrentHashMap<>();
    private final TokenService tokenService  ;

    public GameWebSocketHandler(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long gameId = getGameId(session);
        Long playerId = getPlayerId(session);

        sessionGameMap.put(session.getId(),gameId);
        sessionPlayerMap.put(session.getId(),playerId);

        GameRoom room = rooms.computeIfAbsent(gameId, GameRoom::new);
        room.addPlayer(session);

        WSMessage wsMessage = new WSMessage();
        wsMessage.setType(WSMessageTypes.PLAYER_JOINED);
        wsMessage.setGameId(gameId);
        wsMessage.setPlayerId(playerId);

        sendToRoom(gameId,wsMessage);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        WSMessage msg = objectMapper.readValue(message.getPayload(), WSMessage.class);

        switch (msg.getType()) {

            case CHAT:
                handleChat(msg);
                break;
            case MOVE_TOKEN:
                handleMoveToken(msg);
                break;
            case PLACE_TOKEN:
                handlePlacedToken(msg);
                break;
            case REMOVE_TOKEN:
                handleRemoveToken(msg);
                break;

            default:
                System.out.println("Type inconnu : " + msg.getType());
        }
    }

    private void handleChat(WSMessage msg) throws Exception {
        sendToRoom(msg.getGameId(), msg);
    }

    private void handleMoveToken(WSMessage msg) throws Exception {

        TokenPlaced tokenPlaced = objectMapper.convertValue(msg.getData(), TokenPlaced.class);
        tokenPlaced= tokenService.UpdateTokenPlaced(tokenPlaced);
        msg.setData(tokenPlaced);
        sendToRoom(msg.getGameId(), msg);
    }

    private void handlePlacedToken(WSMessage msg) throws  Exception {
        TokenPlaced tokenPlaced = objectMapper.convertValue(msg.getData(), TokenPlaced.class);
        tokenPlaced= tokenService.CreateTokenPlaced(tokenPlaced);
        msg.setData(tokenPlaced);
        sendToRoom(msg.getGameId(), msg);
    }

    private  void handleRemoveToken(WSMessage msg) throws  Exception {
        Long id = objectMapper.convertValue(msg.getData(), Long.class);

        tokenService.DeleteTokenPlaced(id);

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
