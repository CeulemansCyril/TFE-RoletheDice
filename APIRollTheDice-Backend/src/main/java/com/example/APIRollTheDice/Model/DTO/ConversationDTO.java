package com.example.APIRollTheDice.Model.DTO;


import java.time.LocalDateTime;
import java.util.List;

public class ConversationDTO {
    private Long id;
    private List<Long> idParticipants;
    private LocalDateTime createdAt;
    private List<Long> IdMessages;

    public ConversationDTO() {
    }

    public ConversationDTO(Long id, List<Long> idParticipants, LocalDateTime createdAt, List<Long> idMessages) {
        this.id = id;
        this.idParticipants = idParticipants;
        this.createdAt = createdAt;
        IdMessages = idMessages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIdParticipants() {
        return idParticipants;
    }

    public void setIdParticipants(List<Long> idParticipants) {
        this.idParticipants = idParticipants;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Long> getIdMessages() {
        return IdMessages;
    }

    public void setIdMessages(List<Long> idMessages) {
        IdMessages = idMessages;
    }
}
