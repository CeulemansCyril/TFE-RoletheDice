package com.example.APIRollTheDice.Mapper.Conversation;

import com.example.APIRollTheDice.Model.DTO.ConversationDTO.ConversationReadDTO;
import com.example.APIRollTheDice.Model.Obj.Conversation.ConversationRead;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConversationReadMapper {

    @Mapping(source = "conversation.id",target = "conversationId")
    @Mapping(source = "user.id",target = "userId")
    ConversationReadDTO toDTO(ConversationRead conversationRead);

    @Mapping(target = "conversation", ignore = true)
    @Mapping(target = "user", ignore = true)
    ConversationRead toEntity(ConversationReadDTO conversationReadDTO);
}
