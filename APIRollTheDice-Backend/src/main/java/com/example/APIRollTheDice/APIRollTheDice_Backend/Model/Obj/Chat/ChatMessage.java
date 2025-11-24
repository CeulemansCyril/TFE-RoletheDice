package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String messageContent;
    @Column(nullable = false)
    private boolean isModified=false;

    @Column(nullable = false)
    private LocalDateTime sentAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_chanel_id", nullable = false)
    @JsonIgnore
    private ChatChanel chatChanel;



    public ChatMessage() {
    }

    public ChatMessage(Long id, User sender, String messageContent, boolean isModified, LocalDateTime sentAt, ChatChanel chatChanel) {
        this.id = id;
        this.sender = sender;
        this.messageContent = messageContent;
        this.isModified = isModified;
        this.sentAt = sentAt;
        this.chatChanel = chatChanel;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
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

    public ChatChanel getChatChanel() {
        return chatChanel;
    }

    public void setChatChanel(ChatChanel chatChanel) {
        this.chatChanel = chatChanel;
    }
}
