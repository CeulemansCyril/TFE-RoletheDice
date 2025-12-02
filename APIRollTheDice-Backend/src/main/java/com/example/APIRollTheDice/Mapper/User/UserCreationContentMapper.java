package com.example.APIRollTheDice.Mapper.User;

import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.Obj.User.UserCreationContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCreationContentMapper {
    @Mapping(source = "user.id", target = "userId")
    UserCreationContentDTO toDTO(UserCreationContent userCreationContent);

    @Mapping(target = "user", ignore = true)
    UserCreationContent toEntity(UserCreationContentDTO dto);
}
