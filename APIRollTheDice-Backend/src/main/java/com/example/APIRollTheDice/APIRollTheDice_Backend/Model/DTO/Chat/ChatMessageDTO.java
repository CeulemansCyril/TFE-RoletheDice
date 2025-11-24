package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Chat;


import java.time.LocalDateTime;

public class ChatMessageDTO {
    private Long id;

    private Long idSender;

    private String messageContent;

    private boolean isModified=false;

    private LocalDateTime sentAt = LocalDateTime.now();

    private Long idChatChanel;



    public ChatMessageDTO() {
    }

    public ChatMessageDTO(Long id, Long idSender, String messageContent, boolean isModified, LocalDateTime sentAt, Long idChatChanel) {
        this.id = id;
        this.idSender = idSender;
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

    public Long getIdSender() {
        return idSender;
    }

    public void setIdSender(Long idSender) {
        this.idSender = idSender;
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
