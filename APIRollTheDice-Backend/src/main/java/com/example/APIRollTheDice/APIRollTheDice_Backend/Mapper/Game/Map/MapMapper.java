package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Map;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.MapDTO.MapDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Map.Layout;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Map.Map;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapMapper {

    @Mapping(source = "gameBundle.id", target = "idGameBundle")
    @Mapping(source = "layouts", target = "idLayouts")
    MapDTO toDTO(Map map);

    @Mapping(target = "gameBundle", ignore = true)
    @Mapping(target = "layouts", ignore = true)
    Map toEntity(MapDTO dto);

    default Long mapGameBundleToId(GameBundle gb) {
        return gb != null ? gb.getId() : null;
    }

    default Long mapLayoutToId(Layout layout) {
        return layout != null ? layout.getId() : null;
    }

    default List<Long> mapLayouts(List<Layout> layouts) {
        if (layouts == null) return null;
        return layouts.stream().map(Layout::getId).toList();
    }
}
