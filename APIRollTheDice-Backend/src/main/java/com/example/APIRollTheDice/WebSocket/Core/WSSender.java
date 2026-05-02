package com.example.APIRollTheDice.WebSocket.Core;

import com.example.APIRollTheDice.WebSocket.DTO.WSMessage;
import com.example.APIRollTheDice.WebSocket.GameWebSocketHandler;
import com.example.APIRollTheDice.WebSocket.Object.GameRoom;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

@Service
public class WSSender {
    private final WSSessionRegistry registry;
    private final ObjectMapper mapper = new ObjectMapper();

    public WSSender(WSSessionRegistry registry) {
        this.registry = registry;
    }

    public void sendToRoom(Long gameId, WSMessage payload) throws Exception {
        GameRoom room = registry.getRoom(gameId);
        if (room == null) return;
        room.broadcast(mapper.writeValueAsString(payload));
    }

    public void sendToUser(Long userId, WSMessage msg) throws Exception {

        String json = mapper.writeValueAsString(msg);

        for (Map.Entry<String, Long> entry : registry.getSessionUserMap().entrySet()) {

            if (entry.getValue().equals(userId)) {

                String sessionId = entry.getKey();

                for (GameRoom room : registry.getRooms().values()) {

                    WebSocketSession session = room.getSession(sessionId);

                    if (session != null && session.isOpen()) {
                        session.sendMessage(new TextMessage(json));
                    }
                }
            }
        }
    }

    public <T> T getFromPayload(WSMessage msg, String key, Class<T> clazz) {
        if (msg.getPayload() == null || !msg.getPayload().containsKey(key)) return null;
        Object obj = msg.getPayload().get(key);
        if (obj == null) return null;
        return clazz.cast(obj);
    }
}
