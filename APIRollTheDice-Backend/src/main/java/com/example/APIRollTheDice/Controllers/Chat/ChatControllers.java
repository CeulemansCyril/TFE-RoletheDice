package com.example.APIRollTheDice.Controllers.Chat;

import com.example.APIRollTheDice.Model.DTO.Chat.ChatChanelDTO;
import com.example.APIRollTheDice.Model.DTO.Chat.ChatMessageDTO;
import com.example.APIRollTheDice.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.Model.Obj.Chat.ChatMessage;
import com.example.APIRollTheDice.Service.Chat.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/chat")
public class ChatControllers {
    private final ChatService chatService;

    public ChatControllers(ChatService chatService) {
        this.chatService = chatService;
    }


    // ------------------- ChatChanel -------------------------

    @PostMapping("/CreateChat")
    public ResponseEntity<ChatChanelDTO> CreateChat(@RequestBody ChatChanelDTO chatChanelDTO){
        ChatChanel chatChanel = chatService.CreateChatChanelle( chatService.ChanelDTOToEntity(chatChanelDTO));
        return ResponseEntity.ok(chatService.ChanelEntityToDTO(chatChanel));
    }

    @PutMapping("/UpdateChat")
    public ResponseEntity<ChatChanelDTO> UpdateChat(@RequestBody ChatChanelDTO chatChanelDTO){
        ChatChanel chatChanel = chatService.UpdateChatChanel( chatService.ChanelDTOToEntity(chatChanelDTO));
        return ResponseEntity.ok(chatService.ChanelEntityToDTO(chatChanel));
    }

    @DeleteMapping("/DeleteChat/{id} ")
    public ResponseEntity<String> DeleteChat(@PathVariable Long id) {
        chatService.DeleteChatChannel(id);
        return ResponseEntity.ok("Chat Chanel deleted successfully.");
    }

    @GetMapping("/GetALlChatByGameId/{id}")
    public ResponseEntity<List<ChatChanelDTO>> GetALlChatByGameId(@PathVariable Long id){
        List<ChatChanel> chatChanels = chatService.GetAllChatChannelByIdGame(id);

        List<ChatChanelDTO> dts=  chatChanels.stream()
                .map(chatService::ChanelEntityToDTO)
                .toList();

        return ResponseEntity.ok(dts);
    }


    // ------------------- ChatMessage -------------------------

    @PostMapping("/CreateMessage")
    public ResponseEntity<ChatMessageDTO>CreateMessage(@RequestBody ChatMessageDTO chatMessageDTO){
        ChatMessage chatMessage = chatService.CreateChatMessage( chatService.ChatMessDTOToEntity(chatMessageDTO));
        return ResponseEntity.ok(chatService.ChatMessToDTO(chatMessage));
    }

    @PostMapping("/CreateManyMessage")
    public ResponseEntity<List<ChatMessageDTO>>CreateManyMessage(@RequestBody List<ChatMessageDTO> chatMessageDTOs) {
        List<ChatMessage> chatMessageList =chatService.CreateManyChatMessage(
                chatMessageDTOs.stream()
                        .map(chatService::ChatMessDTOToEntity)
                        .toList()
        );
        List<ChatMessageDTO> dts = chatMessageList.stream()
                .map(chatService::ChatMessToDTO)
                .toList();
        return ResponseEntity.ok(dts);
    }

    @PutMapping("/UpdateMessage")
    public ResponseEntity<ChatMessageDTO> UpdateMessage(@RequestBody ChatMessageDTO chatMessageDTO) {
        ChatMessage chatMessage = chatService.UpdateChatMessage(chatService.ChatMessDTOToEntity(chatMessageDTO));
        return ResponseEntity.ok(chatService.ChatMessToDTO(chatMessage));
    }

    @DeleteMapping("/DeleteMessage/{id} ")
    public ResponseEntity<String> DeleteMessage(@PathVariable Long id) {
        chatService.DeleteChatMessage(id);
        return ResponseEntity.ok("Chat Message deleted successfully.");
    }

    @GetMapping("/GetAllMessageByChanelId/{id}")
    public ResponseEntity<List<ChatMessageDTO>> GetAllMessageByChanelId(@PathVariable Long id) {
        List<ChatMessage> chatMessages = chatService.GetAllChatMessageByChannelle(id);
        List<ChatMessageDTO> dts = chatMessages.stream()
                .map(chatService::ChatMessToDTO)
                .toList();
        return ResponseEntity.ok(dts);
    }


}
