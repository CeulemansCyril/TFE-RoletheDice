package com.example.APIRollTheDice.APIRollTheDice_Backend.Service;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.ChatInterface.ChatChanelInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.ChatInterface.ChatMessageInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.GameInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.UserRepository;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Chat.ChatChanelMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Chat.ChatMessageMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Chat.ChatChanelDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Chat.ChatMessageDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Agenda.AgendaEvent;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatMessage;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private ChatChanelMapper chatChanelMapper;
    private ChatMessageMapper chatMessageMapper;
    private ChatMessageInterface chatMessageInterface;
    private ChatChanelInterface chatChanelInterface;
    private UserRepository userRepository;
    private GameInterface gameInterface;

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
        Game game = gameInterface.getById(idGame);
        List<ChatChanel> chatChanels = chatChanelInterface.findAllByGame_Id(game.getId());
        return chatChanels;
    }



    public ChatChanel ChanelDTOToEntity(ChatChanelDTO chatChanelDTO){
        ChatChanel chanel = chatChanelMapper.toEntity(chatChanelDTO);
        chanel.setGame(gameInterface.getById(chatChanelDTO.getId()));
        chanel.setMessages(chatMessageInterface.findAllById(chatChanelDTO.getIdMessages()));

        return chanel;
    }

    public ChatChanelDTO ChanelEntityToDTO(ChatChanel chanel){
        ChatChanelDTO chatChanelDTO = chatChanelMapper.toDTO(chanel);
        return chatChanelDTO;
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
        ChatChanel chanel = chatChanelInterface.findById(id).get();
        List<ChatMessage> messageList = chatMessageInterface.findAllByChatChanel(chanel);
        return messageList;
    }


    public ChatMessage ChatMessDTOToEntity(ChatMessageDTO chatMessageDTO){
        ChatMessage chatMessage = chatMessageMapper.toEntity(chatMessageDTO);
        chatMessage.setChatChanel(chatChanelInterface.getById(chatMessageDTO.getIdChatChanel()));
        chatMessage.setSender(userRepository.findById(chatMessageDTO.getIdSender()).get());
        return chatMessage;
    }

    public ChatMessageDTO ChatMessToDTO(ChatMessage chatMessage){
        ChatMessageDTO chatMessageDTO = chatMessageMapper.toDTO(chatMessage);
        return  chatMessageDTO;
    }

}
