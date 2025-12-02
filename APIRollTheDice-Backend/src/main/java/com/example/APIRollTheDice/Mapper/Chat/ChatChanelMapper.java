package com.example.APIRollTheDice.Mapper.Chat;

import com.example.APIRollTheDice.Model.DTO.Chat.ChatChanelDTO;
import com.example.APIRollTheDice.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.Model.Obj.Chat.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatChanelMapper {

    @Mapping(source = "messages", target = "idMessages", qualifiedByName = "mapMessagesToIds")
    @Mapping(source = "game.id", target = "idGame")
    ChatChanelDTO toDTO(ChatChanel chatChanel);

    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "game", ignore = true)
    ChatChanel toEntity(ChatChanelDTO chatChanelDTO);

    @Named("mapMessagesToIds")
    default List<Long> mapMessagesToIds(List<ChatMessage> chatMessages){
        if(chatMessages == null || chatMessages.isEmpty()) return null;
        return chatMessages.stream().map(ChatMessage::getId).toList();
    }
}
