package com.example.APIRollTheDice.WebSocket.Core;


import com.example.APIRollTheDice.WebSocket.Object.GameRoom;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WSSessionRegistry {
    private final Map<Long, GameRoom> rooms = new ConcurrentHashMap<>();

    private final Map<String, Long> sessionGameMap = new ConcurrentHashMap<>();

    private final Map<String, Long> sessionUserMap = new ConcurrentHashMap<>();

    public void registerSession(WebSocketSession session, Long gameId, Long userId) {
        sessionGameMap.put(session.getId(), gameId);
        sessionUserMap.put(session.getId(), userId);
        GameRoom room = rooms.computeIfAbsent(gameId, GameRoom::new);
        room.addPlayer(session);
    }

    public void removeSession(WebSocketSession session) {
        String sessionId = session.getId();

        Long gameId = sessionGameMap.remove(sessionId);
        sessionUserMap.remove(sessionId);

        if (gameId == null) return;

        GameRoom room = rooms.get(gameId);

        if (room != null) {
            room.removePlayer(session);
        }
    }

    public GameRoom getRoom(Long gameId) {
        return rooms.get(gameId);
    }

    public Long getUserId(String sessionId) {
        return sessionUserMap.get(sessionId);
    }

    public Long getGameId(String sessionId) {
        return sessionGameMap.get(sessionId);
    }

    public Map<String, Long> getSessionUserMap() {
        return sessionUserMap;
    }

    public Map<Long, GameRoom> getRooms() {
        return rooms;
    }
}
