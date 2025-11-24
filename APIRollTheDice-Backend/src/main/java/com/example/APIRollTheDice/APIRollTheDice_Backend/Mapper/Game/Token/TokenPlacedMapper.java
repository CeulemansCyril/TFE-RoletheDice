package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Token;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TokenDTO.TokenPlacedDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Map.Layout;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Token.Token;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Token.TokenPlaced;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TokenPlacedMapper {

    @Mapping(source = "layout", target = "idLayout", qualifiedByName = "layoutToId")
    @Mapping(source = "token", target = "idToken", qualifiedByName = "tokenToId")
    TokenPlacedDTO toDTO(TokenPlaced tokenPlaced);

    @Mapping(target = "layout", ignore = true)
    @Mapping(target = "token", ignore = true)
    TokenPlaced toEntity(TokenPlacedDTO tokenPlacedDTO);

    @Named("layoutToId")
    default Long mapLayoutToId(Layout layout) {
        return layout != null ? layout.getId() : null;
    }

    @Named("tokenToId")
    default Long mapTokenToId(Token token) {
        return token != null ? token.getId() : null;
    }
}

