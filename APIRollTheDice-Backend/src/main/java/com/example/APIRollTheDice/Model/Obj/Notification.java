package com.example.APIRollTheDice.Model.Obj;

import com.example.APIRollTheDice.Enum.NotificationType;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private NotificationType type;
    @Column(nullable = false)
    private boolean read;
    @Column(nullable = false)
    private Long receiverId;
    @Column(nullable = false)
    private LocalDateTime timestamp;


    public Notification() {
    }
    public Notification(Long id, String message, NotificationType type, boolean read, Long receiverId, LocalDateTime timestamp) {
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

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
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
