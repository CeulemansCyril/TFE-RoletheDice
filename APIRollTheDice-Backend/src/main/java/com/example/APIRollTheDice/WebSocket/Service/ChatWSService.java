package com.example.APIRollTheDice.WebSocket.Service;

import com.example.APIRollTheDice.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.Model.Obj.Chat.ChatMessage;
import com.example.APIRollTheDice.Service.Chat.ChatService;
import com.example.APIRollTheDice.WebSocket.Core.WSSender;
import com.example.APIRollTheDice.WebSocket.DTO.WSMessage;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class ChatWSService {
    private final WSSender handler;
    private final ChatService chatService;


    public ChatWSService(WSSender handler, ChatService chatService) {
        this.handler = handler;
        this.chatService = chatService;
    }

    public void handle(WSMessage msg) throws Exception {

        Map<String, Object> payload = msg.getPayload();
        if (payload == null) return;

        switch (msg.getType()) {

            case CHAT_MESSAGE:

                CreateChatMessage(msg);
                break;
            case UPDATED_MESSAGE:
                UpdateChatMessage(msg);
                break;
            case DELETED_MESSAGE:
                DeleteChatMessage(msg);
                break;
            case CREATE_CHANNEL:
                CreateChatChannel(msg);
                break;
            case UPDATE_CHANNEL:
                UpdateChatChannel(msg);
                break;
            case DELETE_CHANNEL:
                DeleteChatChannel(msg);
                break;
        }


    }


    private void CreateChatMessage(WSMessage msg) throws Exception {
        ChatMessage chatMessage = handler.getFromPayload(msg, "chatMessage", ChatMessage.class);
        if (chatMessage == null) return;
        chatMessage = chatService.CreateChatMessage(chatMessage);
        msg.setPayload(Map.of("chatMessage", chatMessage));
        handler.sendToRoom(msg.getGameId(), msg);
    }

    private void UpdateChatMessage(WSMessage msg) throws Exception {
        ChatMessage chatMessage = handler.getFromPayload(msg, "chatMessage", ChatMessage.class);
        if (chatMessage == null) return;
        chatMessage = chatService.UpdateChatMessage(chatMessage);
        msg.setPayload(Map.of("chatMessage", chatMessage));
        handler.sendToRoom(msg.getGameId(), msg);
    }

    private void DeleteChatMessage(WSMessage msg) throws Exception {
        ChatMessage chatMessage = handler.getFromPayload(msg, "chatMessage", ChatMessage.class);
        if (chatMessage == null) return;
        chatService.DeleteChatMessage(chatMessage.getId());
        msg.setPayload(Map.of("chatMessage", chatMessage));
        handler.sendToRoom(msg.getGameId(), msg);
    }

    private void CreateChatChannel(WSMessage msg) throws Exception {
        ChatChanel chatChanel = handler.getFromPayload(msg, "chatChanel", ChatChanel.class);
        if (chatChanel == null) return;
        chatChanel = chatService.CreateChatChanelle(chatChanel);
        msg.setPayload(Map.of("chatChanel", chatChanel));
        handler.sendToRoom(msg.getGameId(), msg);
    }

    private void UpdateChatChannel(WSMessage msg) throws Exception {
        ChatChanel chatChanel = handler.getFromPayload(msg, "chatChanel", ChatChanel.class);
        if (chatChanel == null) return;
        chatChanel = chatService.UpdateChatChanelle(chatChanel);
        msg.setPayload(Map.of("chatChanel", chatChanel));
        handler.sendToRoom(msg.getGameId(), msg);
    }

    private void DeleteChatChannel(WSMessage msg) throws Exception {
        ChatChanel chatChanel = handler.getFromPayload(msg, "chatChanel", ChatChanel.class);
        if (chatChanel == null) return;
        chatService.DeleteChatChannel(chatChanel.getId());
        msg.setPayload(Map.of("chatChanel", chatChanel));
        handler.sendToRoom(msg.getGameId(), msg);
    }
}
