package com.example.APIRollTheDice.Service;

import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.ConversationInterface;
import com.example.APIRollTheDice.Interface.MessageInterface;
import com.example.APIRollTheDice.Interface.User.UserRepository;
import com.example.APIRollTheDice.Mapper.ConversationMapper;
import com.example.APIRollTheDice.Model.DTO.ConversationDTO;
import com.example.APIRollTheDice.Model.Obj.Conversation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {
    private final ConversationInterface conversationInterface;
    private final ConversationMapper conversationMapper;

    private final MessageInterface messageInterface;
    private final UserRepository userRepository;

    public ConversationService(ConversationInterface conversationInterface, ConversationMapper conversationMapper, MessageInterface messageInterface, UserRepository userRepository) {
        this.conversationMapper = conversationMapper;
        this.conversationInterface = conversationInterface;
        this.messageInterface = messageInterface;
        this.userRepository = userRepository;
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

    public ConversationDTO ConversationToDTO(Conversation conversation){
        return conversationMapper.toDTO(conversation);
    }

    public Conversation ConversationDTOToEntity(ConversationDTO conversationDTO){
        Conversation conversation = conversationMapper.toEntity(conversationDTO);

        if(conversationDTO.getIdParticipants() !=null) {
            conversation.setParticipants(userRepository.findAllById(conversationDTO.getIdParticipants()));
        }

        if(conversationDTO.getIdMessages() != null){
            conversation.setMessages(messageInterface.findAllByConversationId(conversationDTO.getId()));
        }

        return conversation;
    }

}
