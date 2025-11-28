package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.MessageDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(source = "conversation.id", target = "idConversation")
    MessageDTO toDTO(Message message);

    @Mapping(target = "conversation", ignore = true)
    Message toEntity(MessageDTO dto);
}
