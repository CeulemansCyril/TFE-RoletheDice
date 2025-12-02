package com.example.APIRollTheDice.Mapper.Game.Map;

import com.example.APIRollTheDice.Model.DTO.GameDTO.MapDTO.LayoutDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Map.Layout;
import com.example.APIRollTheDice.Model.Obj.Game.Token.TokenPlaced;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LayoutMapper {

    @Mapping(source = "map.id", target = "idMap")
    @Mapping(source = "tokens", target = "idTokenPlaced", qualifiedByName = "mapTokenPlacedToIds")
    LayoutDTO toDTO(Layout layout);

    @Mapping(target = "map", ignore = true)
    @Mapping(target = "tokens", ignore = true)
    Layout toEntity(LayoutDTO layoutDTO);

    @Named("mapTokenPlacedToIds")
    default List<Long> mapTokenPlacedToIds(List<TokenPlaced> tokens) {
        return tokens==null ? null : tokens.stream().map(TokenPlaced::getId).toList();
    }

}
