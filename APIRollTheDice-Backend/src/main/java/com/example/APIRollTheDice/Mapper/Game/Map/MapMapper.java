package com.example.APIRollTheDice.Mapper.Game.Map;

import com.example.APIRollTheDice.Model.DTO.GameDTO.MapDTO.MapDTO;
import com.example.APIRollTheDice.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.Model.Obj.Game.Map.Layout;
import com.example.APIRollTheDice.Model.Obj.Game.Map.Map;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapMapper {

    @Mapping(source = "gameBundle", target = "idGameBundle", qualifiedByName = "mapGameBundleToId")
    @Mapping(source = "layouts", target = "idLayouts", qualifiedByName = "mapLayoutsToId")
    MapDTO toDTO(Map map);

    @Mapping(target = "gameBundle", ignore = true)
    @Mapping(target = "layouts", ignore = true)
    Map toEntity(MapDTO dto);


    @Named("mapLayoutsToId")
    default List<Long> mapLayoutsToId(List<Layout> layouts) {
        if (layouts == null) return null;
        return layouts.stream().map(Layout::getId).toList();
    }

    @Named("mapGameBundleToId")
    default List<Long> mapGameBundleToId(List<GameBundle> gameBundle) {
         if(gameBundle == null) return null;
         return gameBundle.stream().map(GameBundle::getId).toList();

    }
}
