package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Conversation;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Message;

import java.time.LocalDateTime;

public class MessageDTO {
    private long id;
    private String content;
    private String sender;
    private LocalDateTime sentAt;
    private boolean isRead;
    private boolean isModified;
    private String fileURL;
    private Conversation conversation;

    public static MessageDTO from(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setContent(message.getContent());
        messageDTO.setSender(message.getSender());
        messageDTO.setSentAt(message.getSentAt());
        messageDTO.setRead(message.isRead());
        messageDTO.setModified(message.isModified());
        messageDTO.setFileURL(message.getFileURL());
        messageDTO.setConversation(message.getConversation());
        return messageDTO;
    }

    public MessageDTO() {
    }

    public MessageDTO(long id, String content, String sender, LocalDateTime sentAt, boolean isRead, boolean isModified, String fileURL, Conversation conversation) {
        this.id = id;
        this.content = content;
        this.sender = sender;
        this.sentAt = sentAt;
        this.isRead = isRead;
        this.isModified = isModified;
        this.fileURL = fileURL;
        this.conversation = conversation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
