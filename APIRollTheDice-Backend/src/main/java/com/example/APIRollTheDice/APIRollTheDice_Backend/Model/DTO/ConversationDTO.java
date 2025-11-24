package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO;


import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Conversation;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Message;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;

import java.time.LocalDateTime;
import java.util.List;

public class ConversationDTO {
    private Long id;
    private List<User> participants;
    private LocalDateTime createdAt;
    private List<Message> messages;

    public ConversationDTO() {
    }

    public ConversationDTO(Long id, List<User> participants, LocalDateTime createdAt, List<Message> messages) {
        this.id = id;
        this.participants = participants;
        this.createdAt = createdAt;
        this.messages = messages;
    }

    public static ConversationDTO from(Conversation conversation) {
         ConversationDTO conversationDTO = new ConversationDTO();
         conversationDTO.setId(conversation.getId());
         conversationDTO.setParticipants(conversation.getParticipants());
         conversationDTO.setCreatedAt(conversation.getCreatedAt());
            conversationDTO.setMessages(conversation.getMessages());
         return conversationDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
