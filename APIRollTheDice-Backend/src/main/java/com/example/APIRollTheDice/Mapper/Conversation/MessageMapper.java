package com.example.APIRollTheDice.Mapper.Conversation;

import com.example.APIRollTheDice.Model.DTO.ConversationDTO.MessageDTO;
import com.example.APIRollTheDice.Model.Obj.Conversation.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(source = "conversation.id", target = "idConversation")
    @Mapping(source = "sender.id", target = "sender")
    MessageDTO toDTO(Message message);

    @Mapping(target = "conversation", ignore = true)
    @Mapping(target = "sender", ignore = true)
    Message toEntity(MessageDTO dto);
}
