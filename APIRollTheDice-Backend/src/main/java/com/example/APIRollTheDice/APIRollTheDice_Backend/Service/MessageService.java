package com.example.APIRollTheDice.APIRollTheDice_Backend.Service;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.ConversationInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.MessageInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.MessageMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.MessageDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageInterface messageInterface;
    private final MessageMapper messageMapper;

    private final ConversationInterface conversationInterface;

    public MessageService(MessageInterface messageInterface, MessageMapper messageMapper, ConversationInterface conversationInterface) {
        this.conversationInterface = conversationInterface;
        this.messageMapper = messageMapper;
        this.messageInterface = messageInterface;
    }

    public Message createMessage(Message message) {
        return messageInterface.save(message);
    }

    public Message getMessageById(long id) {
        return messageInterface.findById(id);
    }

    public List<Message> getMessagesByConversationId(long conversationId) {
        return messageInterface.findAllByConversationId(conversationId);
    }

    public Message updateMessage(Message message) {
        Message existingMessage = messageInterface.findById(message.getId());
        if (existingMessage == null) {
            throw new NotFoundException("Message not found ");
        }
        existingMessage.setContent(message.getContent());
        existingMessage.setRead(message.isRead());
        existingMessage.setModified(message.isModified());
        existingMessage.setFileURL(message.getFileURL());

        return messageInterface.save(existingMessage);
    }

    public void deleteMessage(long id) {
        if (messageInterface.existsById(id)) {
            messageInterface.deleteById(id);
        } else {
            throw new NotFoundException("Message not found ");
        }
    }

    public boolean messageExists(long id) {
        return messageInterface.existsById(id);
    }

    public MessageDTO MessageToDTO(Message message) {
        return messageMapper.toDTO(message);
    }

    public Message MessageDTOToEntity(MessageDTO dto) {
        Message message = messageMapper.toEntity(dto);
        if (dto.getIdConversation() != null) {
            message.setConversation(conversationInterface.findById(dto.getIdConversation()).orElseThrow(() -> new NotFoundException("Conversation not found")));
        }
        return message;
    }

}
