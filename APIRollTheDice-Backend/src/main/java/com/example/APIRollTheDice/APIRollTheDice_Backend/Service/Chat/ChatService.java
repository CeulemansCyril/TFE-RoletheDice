package com.example.APIRollTheDice.APIRollTheDice_Backend.Service.Chat;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.ChatInterface.ChatChanelInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.ChatInterface.ChatMessageInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.GameInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.User.UserRepository;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Chat.ChatChanelMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Chat.ChatMessageMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Chat.ChatChanelDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Chat.ChatMessageDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final ChatChanelMapper chatChanelMapper;
    private final ChatMessageMapper chatMessageMapper;
    private final ChatMessageInterface chatMessageInterface;
    private final ChatChanelInterface chatChanelInterface;
    private final UserRepository userRepository;
    private final GameInterface gameInterface;

    public ChatService(ChatChanelMapper chatChanelMapper, ChatMessageMapper chatMessageMapper, ChatMessageInterface chatMessageInterface, ChatChanelInterface chatChanelInterface, UserRepository userRepository, GameInterface gameInterface) {
        this.chatChanelMapper = chatChanelMapper;
        this.chatMessageMapper = chatMessageMapper;
        this.chatMessageInterface = chatMessageInterface;
        this.chatChanelInterface = chatChanelInterface;
        this.userRepository = userRepository;
        this.gameInterface = gameInterface;
    }

    public ChatChanel CreateChatChanelle(ChatChanel chatChanel){
        return chatChanelInterface.save(chatChanel);
    }

    public ChatChanel UpdateChatChanelle(Long id, ChatChanel chatChanel){
        if(chatChanelInterface.existsById(id)){
           return chatChanelInterface.save(chatChanel);
        }else{
           throw new NotFoundException("Chat channel not found");
        }
    }

    public ChatChanel UpdateChatChanel(ChatChanel chatChanel){
        ChatChanel existing = chatChanelInterface.findById(chatChanel.getId())
                .orElseThrow(() -> new NotFoundException("ChatChanel not found"));

        existing.setName(chatChanel.getName());
        existing.setMessages(chatChanel.getMessages());


        return chatChanelInterface.save(existing);
    }

    public void DeleteChatChannel(ChatChanel chatChanel){
        if(chatChanelInterface.existsById(chatChanel.getId())){
             chatChanelInterface.delete(chatChanel);
        }else{
            throw new NotFoundException("Chat channel not found");
        }
    }

    public List<ChatChanel> GetAllChatChannelByIdGame(Long idGame){

        return chatChanelInterface.findAllByGame_Id(idGame);
    }



    public ChatChanel ChanelDTOToEntity(ChatChanelDTO chatChanelDTO){
        ChatChanel chanel = chatChanelMapper.toEntity(chatChanelDTO);

        if(chatChanelDTO.getId() != null) chanel.setGame(gameInterface.findById(chatChanelDTO.getId()).orElseThrow(()-> new NotFoundException("Game not found")));

        return chanel;
    }

    public ChatChanelDTO ChanelEntityToDTO(ChatChanel chanel){
        return  chatChanelMapper.toDTO(chanel);
    }



    /// /////////////////////////////////////
    /// /////////////////////////////////////

    public ChatMessage CreateChatMessage(ChatMessage chatMessage){
        return chatMessageInterface.save(chatMessage);
    }



    public ChatMessage UpdateChatMessage(ChatMessage chatMessage){
        ChatMessage existing = chatMessageInterface.findById(chatMessage.getId())
                .orElseThrow(() -> new NotFoundException("Book not found"));

        existing.setModified(chatMessage.isModified());
        existing.setMessageContent(chatMessage.getMessageContent());

        return chatMessageInterface.save(existing);
    }

    public void DeleteChatMessage(ChatMessage chatMessage){
        if(chatMessageInterface.existsById(chatMessage.getId())){
            chatMessageInterface.delete(chatMessage);
        }else{
            throw  new NotFoundException("Chat message not found");
        }
    }

    public List<ChatMessage> GetAllChatMessageByChannelle(Long id){

        return chatMessageInterface.findAllByChatChanel_Id(id);
    }


    public ChatMessage ChatMessDTOToEntity(ChatMessageDTO chatMessageDTO){
        ChatMessage chatMessage = chatMessageMapper.toEntity(chatMessageDTO);
        chatMessage.setChatChanel(chatChanelInterface.findById(chatMessageDTO.getIdChatChanel()).orElseThrow(()-> new NotFoundException("Chat channel not found")));
        chatMessage.setSender(userRepository.findById(chatMessageDTO.getIdSender()).orElseThrow(()-> new NotFoundException("User not found")));
        return chatMessage;
    }

    public ChatMessageDTO ChatMessToDTO(ChatMessage chatMessage){
        return   chatMessageMapper.toDTO(chatMessage);
    }

}
