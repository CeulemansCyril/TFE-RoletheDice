package com.example.APIRollTheDice.Model.DTO.Chat;


import com.example.APIRollTheDice.Model.DTO.UserDTo.UserIdentifantData;

import java.time.LocalDateTime;

public class ChatMessageDTO {
    private Long id;

    private UserIdentifantData sender;

    private String messageContent;

    private boolean isModified=false;

    private LocalDateTime sentAt = LocalDateTime.now();

    private Long idChatChanel;



    public ChatMessageDTO() {
    }

    public ChatMessageDTO(Long id, UserIdentifantData sender, String messageContent, boolean isModified, LocalDateTime sentAt, Long idChatChanel) {
        this.id = id;
        this.sender =sender;
        this.messageContent = messageContent;
        this.isModified = isModified;
        this.sentAt = sentAt;
        this.idChatChanel = idChatChanel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserIdentifantData getSender() {
        return sender;
    }

    public void setSender(UserIdentifantData sender) {
        this.sender = sender;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public Long getIdChatChanel() {
        return idChatChanel;
    }

    public void setIdChatChanel(Long idChatChanel) {
        this.idChatChanel = idChatChanel;
    }
}
