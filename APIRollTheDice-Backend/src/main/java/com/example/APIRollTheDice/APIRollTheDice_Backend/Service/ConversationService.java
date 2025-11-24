package com.example.APIRollTheDice.APIRollTheDice_Backend.Service;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.ConversationInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Conversation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {
    private final ConversationInterface conversationInterface;

    public ConversationService(ConversationInterface conversationInterface) {
        this.conversationInterface = conversationInterface;
    }

    public void CreatConversation(Conversation conversation) {
        if (conversation != null && conversation.getParticipants() != null && !conversation.getParticipants().isEmpty()) {
            conversationInterface.save(conversation);
        } else {
            throw new IllegalArgumentException("Conversation must have at least one participant.");
        }
    }

    public Conversation getConversationById(Long id) {
        return conversationInterface.findById(id)
                .orElseThrow(() -> new NotFoundException("Conversation not found  "));
    }

    public List<Conversation>getAllConversationsByParticipantId(Long participantId) {
        return conversationInterface.findAllByParticipantsId(participantId);
    }

    public void updateConversation(Conversation conversation) {

            if (conversationInterface.existsById(conversation.getId())) {
                conversationInterface.save(conversation);
            } else {
                throw new NotFoundException("Conversation not found for update");
            }

    }

    public void deleteConversation(Long id) {
        if (conversationInterface.existsById(id)) {
            conversationInterface.deleteById(id);
        } else {
            throw new NotFoundException("Conversation not found for deletion");
        }
    }

    public boolean conversationExists(Long id) {
        return conversationInterface.existsById(id);
    }

}
