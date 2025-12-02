package com.example.APIRollTheDice.Model.DTO;

import com.example.APIRollTheDice.Enum.NotificationType;


import java.time.LocalDateTime;

public class NotificationDTO {
    private Long id;
    private String message;
    private NotificationType type;
    private boolean read;
    private Long receiverId;
    private LocalDateTime timestamp;




    public NotificationDTO() {
    }

    public NotificationDTO(Long id, String message, NotificationType type, boolean read, Long receiverId, LocalDateTime timestamp) {
        this.id = id;
        this.message = message;
        this.type = type;
        this.read = read;
        this.receiverId = receiverId;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
