package com.example.APIRollTheDice.WebSocket.Object;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameRoom {

    private Long gameId;

    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    public GameRoom(Long gameId) {
        this.gameId = gameId;
    }

    public void addPlayer(WebSocketSession session) {
        sessions.add(session);
        sessionMap.put(session.getId(), session);
    }

    public void removePlayer(WebSocketSession session) {
        sessions.remove(session);
        sessionMap.remove(session.getId());
    }

    public void broadcast(String message) throws IOException {
        for (WebSocketSession session : sessions) {

            if (!session.isOpen()) {
                removePlayer(session);
                continue;
            }

            session.sendMessage(new TextMessage(message));
        }
    }

    public WebSocketSession getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    public boolean isEmpty() {
        return sessions.isEmpty();
    }
}