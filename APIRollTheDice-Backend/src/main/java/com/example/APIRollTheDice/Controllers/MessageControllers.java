package com.example.APIRollTheDice.Controllers;

import com.example.APIRollTheDice.Model.DTO.MessageDTO;
import com.example.APIRollTheDice.Model.Obj.Message;
import com.example.APIRollTheDice.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageControllers {
    private final MessageService messageService;

    @Autowired
    public MessageControllers(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/create")
    public ResponseEntity<MessageDTO> createMessage(@RequestBody MessageDTO message) {
        Message message1 =  messageService.createMessage(messageService.MessageDTOToEntity(message));
        return ResponseEntity.ok(messageService.MessageToDTO(message1));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable Long id) {
        Message message = messageService.getMessageById(id);
        return ResponseEntity.ok(messageService.MessageToDTO(message));
    }

    @GetMapping("/getAllByConversationId/{conversationId}")
    public ResponseEntity<List<MessageDTO>> getMessagesByConversationId(@PathVariable Long conversationId) {
        List<Message> messages = messageService.getMessagesByConversationId(conversationId);
        if (messages != null && !messages.isEmpty()) {
            return ResponseEntity.ok(messages.stream().map(messageService::MessageToDTO).toList());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateMessage(@RequestBody Message message) {
            messageService.updateMessage(message);
            return ResponseEntity.ok("Message updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
            messageService.deleteMessage(id);
            return ResponseEntity.ok("Message deleted successfully");
    }
}
