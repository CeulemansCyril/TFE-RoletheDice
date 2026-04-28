package com.example.APIRollTheDice.Service;

import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.ConversationInterface.ConversationInterface;
import com.example.APIRollTheDice.Interface.ConversationInterface.ConversationReadInterface;
import com.example.APIRollTheDice.Interface.ConversationInterface.MessageInterface;
import com.example.APIRollTheDice.Interface.User.UserRepository;
import com.example.APIRollTheDice.Mapper.Conversation.ConversationMapper;
import com.example.APIRollTheDice.Mapper.Conversation.ConversationReadMapper;
import com.example.APIRollTheDice.Model.DTO.ConversationDTO.ConversationDTO;
import com.example.APIRollTheDice.Model.DTO.ConversationDTO.ConversationReadDTO;
import com.example.APIRollTheDice.Model.Obj.Conversation.Conversation;
import com.example.APIRollTheDice.Model.Obj.Conversation.ConversationRead;
import com.example.APIRollTheDice.Model.Obj.User.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConversationService {
    private final ConversationInterface conversationInterface;
    private final ConversationMapper conversationMapper;

    private final ConversationReadInterface conversationReadInterface;
    private final ConversationReadMapper conversationReadMapper;

    private final MessageInterface messageInterface;
    private final UserRepository userRepository;

    public ConversationService(ConversationInterface conversationInterface, ConversationMapper conversationMapper,
                               MessageInterface messageInterface, UserRepository userRepository,ConversationReadInterface conversationReadInterface,
                               ConversationReadMapper conversationReadMapper) {
        this.conversationMapper = conversationMapper;
        this.conversationInterface = conversationInterface;
        this.messageInterface = messageInterface;
        this.userRepository = userRepository;
        this.conversationReadInterface =conversationReadInterface;
        this.conversationReadMapper = conversationReadMapper;
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
            deleteConversationReadByConversationId(id);
        } else {
            throw new NotFoundException("Conversation not found for deletion");
        }
    }

    public Conversation findOrCreateConversation(Long senderId, Long recipientId) {
        List<Conversation> conversations = conversationInterface.findAllByParticipantsId(senderId);
        for (Conversation conversation : conversations) {
            if (conversation.getParticipants().stream().anyMatch(user -> user.getId().equals(recipientId))) {
                return conversation;
            }
        }
        Conversation newConversation = new Conversation();
        newConversation.setParticipants(List.of(userRepository.findById(senderId).orElseThrow(() -> new NotFoundException("Sender not found")), userRepository.findById(recipientId).orElseThrow(() -> new NotFoundException("Recipient not found"))));
        newConversation = conversationInterface.save(newConversation);

        for(User user : newConversation.getParticipants()){
            ConversationRead conversationRead = new ConversationRead();
            conversationRead.setUser(user);
            conversationRead.setConversation(newConversation);
            createConversationRead(conversationRead);
        }

        return newConversation;
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void createConversationRead(ConversationRead conversationRead){
        conversationReadInterface.save(conversationRead);
    }


    public void updateLastRead( Long conversationId, Long userId, Long lastMessageId) {

        ConversationRead read = conversationReadInterface
                .findByConversationIdAndUserId(conversationId, userId)
                .orElseGet(() -> {
                    ConversationRead cr = new ConversationRead();
                    cr.setConversation(
                            getConversationById(conversationId)
                    );
                    cr.setUser(userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found")));
                    return cr;
                });

        if (read.getLastMessageId() != null && read.getLastMessageId() >= lastMessageId) return;

        read.setLastMessageId(lastMessageId);
        read.setLastReadAt(LocalDateTime.now());

        conversationReadInterface.save(read);
    }

    public void deleteConversationReadByConversationId(Long id){
        conversationReadInterface.deleteConversationReadByConversation_Id(id);
    }





    public ConversationReadDTO ConversationReadToDTO (ConversationRead conversationRead){
        ConversationReadDTO conversationReadDTO = new ConversationReadDTO();
        conversationReadDTO= conversationReadMapper.toDTO(conversationRead);
        return conversationReadDTO;
    }

    public ConversationRead ConversationReadDTOToEntity(ConversationReadDTO conversationReadDTO){
        ConversationRead conversationRead = new ConversationRead();
        conversationRead = conversationReadMapper.toEntity(conversationReadDTO);

        if(conversationReadDTO.getConversationId() != null){
            conversationRead.setConversation(getConversationById(conversationReadDTO.getConversationId()));
        }

        if (conversationReadDTO.getUserId() != null){
            conversationRead.setUser(userRepository.findById(conversationReadDTO.getUserId()).orElseThrow(()-> new NotFoundException("User not found")));
        }

        return conversationRead;
    }


}
