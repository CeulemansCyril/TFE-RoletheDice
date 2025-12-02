package com.example.APIRollTheDice.Mapper;

import com.example.APIRollTheDice.Model.DTO.FriendRequestDTO;
import com.example.APIRollTheDice.Model.Obj.FriendRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FriendRequestMapper {
    @Mapping(source = "sender.id", target = "idSender")
    @Mapping(source = "receiver.id", target = "idReceiver")
    FriendRequestDTO toDTO(FriendRequest friendRequest);


    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "receiver", ignore = true)
    FriendRequest toEntity(FriendRequestDTO dto);
}
