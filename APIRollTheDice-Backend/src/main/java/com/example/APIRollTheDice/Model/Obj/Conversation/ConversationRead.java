package com.example.APIRollTheDice.Model.Obj.Conversation;

import com.example.APIRollTheDice.Model.Obj.User.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(
        name = "conversation_reads",
        uniqueConstraints = @UniqueConstraint(columnNames = {
                "conversation_id",
                "user_id"
        })
)
public class ConversationRead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    private Long lastMessageId;
    private LocalDateTime lastReadAt;

    public ConversationRead() {
    }

    public ConversationRead(Conversation conversation, Long id, User user, Long lastMessageId, LocalDateTime lastReadAt) {
        this.conversation = conversation;
        this.id = id;
        this.user = user;
        this.lastMessageId = lastMessageId;
        this.lastReadAt = lastReadAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    public LocalDateTime getLastReadAt() {
        return lastReadAt;
    }

    public void setLastReadAt(LocalDateTime lastReadAt) {
        this.lastReadAt = lastReadAt;
    }
}
