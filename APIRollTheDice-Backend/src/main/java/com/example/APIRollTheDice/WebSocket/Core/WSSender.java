package com.example.APIRollTheDice.WebSocket.Core;

import com.example.APIRollTheDice.WebSocket.DTO.WSMessage;
import com.example.APIRollTheDice.WebSocket.GameWebSocketHandler;
import org.springframework.stereotype.Service;

@Service
public class WSSender {
    private final GameWebSocketHandler handler;

    public WSSender(GameWebSocketHandler handler) {
        this.handler = handler;
    }

        public void sendToRoom(Long gameId, WSMessage payload) throws Exception {
            handler.OutsiderSendToRoom(gameId, payload);
        }

        public void sendToUser(Long userId, WSMessage payload) throws Exception {
            handler.OutsiderSendToUser(userId, payload);
        }

        public <T> T getFromPayload(WSMessage msg, String key, Class<T> clazz) {
            if (msg.getPayload() == null || !msg.getPayload().containsKey(key)) return null;
            Object obj = msg.getPayload().get(key);
            if (obj == null) return null;
            return clazz.cast(obj);
        }
}
