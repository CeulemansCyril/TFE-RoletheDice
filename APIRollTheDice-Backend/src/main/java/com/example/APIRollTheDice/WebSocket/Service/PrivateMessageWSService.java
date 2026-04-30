package com.example.APIRollTheDice.WebSocket.Service;

import com.example.APIRollTheDice.Model.Obj.Conversation.Conversation;
import com.example.APIRollTheDice.Model.Obj.Conversation.Message;
import com.example.APIRollTheDice.Model.Obj.User.User;
import com.example.APIRollTheDice.Service.ConversationService;
import com.example.APIRollTheDice.Service.MessageService;
import com.example.APIRollTheDice.Service.User.UserService;
import com.example.APIRollTheDice.WebSocket.Core.WSSender;
import com.example.APIRollTheDice.WebSocket.DTO.WSMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class PrivateMessageWSService {
    private final WSSender handler;
    private final ConversationService conversationService;
    private final MessageService messageService;
    private final UserService userService;

    public PrivateMessageWSService(WSSender handler, ConversationService conversationService, MessageService messageService, UserService userService) {
        this.userService = userService;
        this.handler = handler;
        this.conversationService = conversationService;
        this.messageService = messageService;
    }

    public void handle(WSMessage msg) throws Exception {
        switch (msg.getType()) {
            case PRIVATE_MESSAGE:
                createPrivateMessage(msg);
                break;
            case UPDATE_PRIVATE_MESSAGE:
                updatePrivateMessage(msg);
                break;
            case DELETE_PRIVATE_MESSAGE:
                deletePrivateMessage(msg);
                break;
            case READ_PRIVATE_MESSAGE:
                readPrivateMessage(msg);
                break;
        }
    }

    private void createPrivateMessage(WSMessage msg) throws Exception {
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
            handler.sendToUser(participant.getId(), msg);
        }
    }

    private void updatePrivateMessage(WSMessage msg) throws Exception {
        Long messageId = handler.getFromPayload(msg, "messageId", Long.class);
        String newContent = handler.getFromPayload(msg, "content", String.class);

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
            handler.sendToUser(participant.getId(), msg);
        }
    }

    private void deletePrivateMessage(WSMessage msg) throws Exception {
        Long messageId = handler.getFromPayload(msg, "messageId", Long.class);
        if (messageId == null) return;

        Message existingMessage = messageService.getMessageById(messageId);

        if (!existingMessage.getSender().getId().equals(msg.getSenderUserId())) {
            return;
        }

        Conversation conversation = existingMessage.getConversation();

        messageService.deleteMessage(messageId);

        msg.setPayload(Map.of("messageId", messageId));

        for (User participant : conversation.getParticipants()) {
            handler.sendToUser(participant.getId(), msg);
        }
    }

    private void readPrivateMessage(WSMessage msg) throws Exception {
        Long conversationId = handler.getFromPayload(msg, "conversationId", Long.class);
        Long lastMessageId = handler.getFromPayload(msg, "lastMessageId", Long.class);
        Long userId = msg.getSenderUserId();

        if (conversationId == null || lastMessageId == null) return;

        conversationService.updateLastRead(conversationId, userId, lastMessageId);

        msg.setPayload(Map.of(
                "conversationId", conversationId,
                "userId", userId,
                "lastReadMessageId", lastMessageId
        ));

        Conversation conversationReturn = conversationService.getConversationById(conversationId);

        for (User participant : conversationReturn.getParticipants()) {
            if (!participant.getId().equals(userId)) {
                handler.sendToUser(participant.getId(), msg);
            }
        }
    }


}
