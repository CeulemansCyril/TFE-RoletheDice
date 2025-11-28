package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Token;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TokenDTO.TokenDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.CustomObject;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Token.Token;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    @Mapping(source = "owner",target = "idOwner")
    @Mapping(source = "fiche",target = "idFiche")
    @Mapping(source = "gameBundle",target = "idGameBundle")
    TokenDTO toDTO(Token token);

    @Mapping(target = "owner",ignore = true)
    @Mapping(target = "fiche",ignore = true)
    @Mapping(target = "gameBundle",ignore = true)
    Token toEntity(TokenDTO dto);


}

