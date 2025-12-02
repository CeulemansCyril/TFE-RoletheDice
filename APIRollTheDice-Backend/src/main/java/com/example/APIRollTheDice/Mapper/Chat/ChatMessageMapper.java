package com.example.APIRollTheDice.Mapper.Chat;

import com.example.APIRollTheDice.Model.DTO.Chat.ChatMessageDTO;
import com.example.APIRollTheDice.Model.Obj.Chat.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {

    @Mapping(source = "sender.id", target = "idSender")
    @Mapping(source = "chatChanel.id", target = "idChatChanel")
    ChatMessageDTO toDTO(ChatMessage chatMessage);

    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "chatChanel", ignore = true)
    ChatMessage toEntity(ChatMessageDTO chatMessageDTO);
}

