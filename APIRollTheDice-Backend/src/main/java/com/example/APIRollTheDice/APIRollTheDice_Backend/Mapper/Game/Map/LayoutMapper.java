package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Map;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.MapDTO.LayoutDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Map.Layout;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Map.Map;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Token.Token;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Token.TokenPlaced;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LayoutMapper {

    @Mapping(source = "map.id", target = "idMap")
    @Mapping(source = "tokens", target = "idTokenPlaced")
    LayoutDTO toDTO(Layout layout);

    @Mapping(target = "map", ignore = true)
    @Mapping(target = "tokens", ignore = true)
    Layout toEntity(LayoutDTO layoutDTO);

    // mapping pour la list<TokenPlaced> → list<Long>
    default Long mapTokenPlaced(TokenPlaced token) {
        return token != null ? token.getId() : null;
    }

    // map id du layout.map
    default Long mapMapToId(Map map) {
        return map != null ? map.getId() : null;
    }
}
