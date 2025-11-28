package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.User;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.UserCreationContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCreationContentMapper {
    @Mapping(source = "user.id", target = "userId")
    UserCreationContentDTO toDTO(UserCreationContent userCreationContent);

    @Mapping(target = "user", ignore = true)
    UserCreationContent toEntity(UserCreationContentDTO dto);
}
