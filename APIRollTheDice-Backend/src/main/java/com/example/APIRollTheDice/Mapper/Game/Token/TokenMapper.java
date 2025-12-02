package com.example.APIRollTheDice.Mapper.Game.Token;

import com.example.APIRollTheDice.Model.DTO.GameDTO.TokenDTO.TokenDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Token.Token;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    @Mapping(source = "owner.id",target = "idOwner")
    @Mapping(source = "fiche.id",target = "idFiche")
    @Mapping(source = "gameBundle.id",target = "idGameBundle")
    TokenDTO toDTO(Token token);

    @Mapping(target = "owner",ignore = true)
    @Mapping(target = "fiche",ignore = true)
    @Mapping(target = "gameBundle",ignore = true)
    Token toEntity(TokenDTO dto);


}

