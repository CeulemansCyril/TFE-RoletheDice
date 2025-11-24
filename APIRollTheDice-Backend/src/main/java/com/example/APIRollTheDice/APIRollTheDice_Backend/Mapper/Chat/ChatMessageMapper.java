package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Chat;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Chat.ChatMessageDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatMessage;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;
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

