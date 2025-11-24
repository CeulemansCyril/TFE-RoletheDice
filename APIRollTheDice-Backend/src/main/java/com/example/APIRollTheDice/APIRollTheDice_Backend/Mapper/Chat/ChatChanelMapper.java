package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Chat;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Chat.ChatChanelDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatMessage;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatChanelMapper {

    @Mapping(source = "messages", target = "idMessages")
    @Mapping(source = "game.id", target = "idGame")
    ChatChanelDTO toDTO(ChatChanel chatChanel);

    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "game", ignore = true)
    ChatChanel toEntity(ChatChanelDTO chatChanelDTO);

    default List<Long> mapMessagesToIds(List<ChatMessage> chatMessages){
        if(chatMessages == null || chatMessages.isEmpty()) return null;
        return chatMessages.stream().map(ChatMessage::getId).toList();
    }
}
