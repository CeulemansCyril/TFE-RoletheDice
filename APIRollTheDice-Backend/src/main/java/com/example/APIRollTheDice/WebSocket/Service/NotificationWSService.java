package com.example.APIRollTheDice.WebSocket.Service;

import com.example.APIRollTheDice.Service.NotificationService;
import com.example.APIRollTheDice.WebSocket.Core.WSSender;
import com.example.APIRollTheDice.WebSocket.DTO.WSMessage;
import org.springframework.stereotype.Service;

@Service
public class NotificationWSService {
    private final WSSender handler;
    private final NotificationService notificationService;

    public NotificationWSService(WSSender handler, NotificationService notificationService) {
        this.handler = handler;
        this.notificationService = notificationService;
    }

    public void handle(WSMessage msg) throws Exception {
        switch (msg.getType()) {
            case NOTIFICATION:
                break;
        }
    }
}
