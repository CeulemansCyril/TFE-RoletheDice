package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.MessageDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String sender;
    @Column(nullable = false)
    private LocalDateTime sentAt;
    @Column(nullable = false)
    private boolean isRead;
    @Column(nullable = false)
    private boolean isModified;
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    private String fileURL;

    private static Message from(MessageDTO message) {
        Message messages = new Message();
        messages.setId(messages.getId());
        messages.setContent(messages.getContent());
        messages.setSender(messages.getSender());
        messages.setSentAt(messages.getSentAt());
        messages.setRead(messages.isRead());
        messages.setModified(messages.isModified());
        messages.setFileURL(messages.getFileURL());
        messages.setConversation(messages.getConversation());
        return messages;

    }

    public Message() {

    }
    public Message(long id,String content, String sender, LocalDateTime sentAt, boolean isRead, boolean isModified, String fileURL, Conversation conversation) {
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
