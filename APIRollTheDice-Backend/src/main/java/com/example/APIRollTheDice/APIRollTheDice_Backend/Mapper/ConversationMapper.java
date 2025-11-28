package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.ConversationDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Conversation;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Message;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConversationMapper {
    @Mapping(source = "participants", target = "idParticipants", qualifiedByName = "usersToIds")
    @Mapping(source = "messages", target = "IdMessages", qualifiedByName = "messagesToIds")
    ConversationDTO toDTO(Conversation conversation);


    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "creator", ignore = true)
    Conversation toEntity(ConversationDTO dto);

    @Named("usersToIds")
    default List<Long> mapUsersToIds(List<User> list) {
        return list == null ? null : list.stream().map(User::getId).toList();
    }

    @Named("messagesToIds")
    default List<Long> mapMessagesToIds(List<Message> list) {
        return list == null ? null : list.stream().map(Message::getId).toList();
    }
}
