package com.example.APIRollTheDice.Model.DTO;

import java.time.LocalDateTime;

public class MessageDTO {
    private long id;
    private String content;
    private String sender;
    private LocalDateTime sentAt;
    private boolean isRead;
    private boolean isModified;
    private String fileURL;
    private Long idConversation;



    public MessageDTO() {
    }

    public MessageDTO(long id, String content, String sender, LocalDateTime sentAt, boolean isRead, boolean isModified, String fileURL, Long idConversation) {
        this.id = id;
        this.content = content;
        this.sender = sender;
        this.sentAt = sentAt;
        this.isRead = isRead;
        this.isModified = isModified;
        this.fileURL = fileURL;
        this.idConversation = idConversation;
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

    public Long getIdConversation() {
        return idConversation;
    }

    public void setIdConversation(Long idConversation) {
        this.idConversation = idConversation;
    }
}
