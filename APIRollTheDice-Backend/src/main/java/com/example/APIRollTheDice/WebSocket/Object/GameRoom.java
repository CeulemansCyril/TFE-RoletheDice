package com.example.APIRollTheDice.WebSocket.Object;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameRoom {

    private Long gameId;
    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public GameRoom(Long gameId) {
        this.gameId = gameId;
    }

    public void addPlayer(WebSocketSession session) {
        sessions.add(session);
    }

    public void removePlayer(WebSocketSession session) {
        sessions.remove(session);
    }

    public void broadcast(String message) throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }

    public boolean isEmpty() {
        return sessions.isEmpty();
    }
}
