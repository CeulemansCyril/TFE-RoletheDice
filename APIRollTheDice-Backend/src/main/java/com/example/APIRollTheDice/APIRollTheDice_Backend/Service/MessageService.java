package com.example.APIRollTheDice.APIRollTheDice_Backend.Service;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.MessageInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageInterface messageInterface;

    public MessageService(MessageInterface messageInterface) {
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
        if (messageInterface.existsById(message.getId())) {
            return messageInterface.save(message);
        } else {
            throw new NotFoundException("Message not found ");
        }
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

}
