package com.example.APIRollTheDice.Model.DTO.ConversationDTO;

import java.time.LocalDateTime;

public class ConversationReadDTO {
    private Long id;
    private Long conversationId;
    private Long userId;
    private Long lastMessageReadId;
    private LocalDateTime lastMessageReadAt;


    public ConversationReadDTO() {
    }

    public ConversationReadDTO(Long id, Long conversationId, Long userId, LocalDateTime lastMessageReadAt, Long lastMessageReadId) {
        this.id = id;
        this.conversationId = conversationId;
        this.userId = userId;
        this.lastMessageReadAt = lastMessageReadAt;
        this.lastMessageReadId = lastMessageReadId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getLastMessageReadAt() {
        return lastMessageReadAt;
    }

    public void setLastMessageReadAt(LocalDateTime lastMessageReadAt) {
        this.lastMessageReadAt = lastMessageReadAt;
    }

    public Long getLastMessageReadId() {
        return lastMessageReadId;
    }

    public void setLastMessageReadId(Long lastMessageReadId) {
        this.lastMessageReadId = lastMessageReadId;
    }
}
