package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Storage;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.Storage.StorageDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Storage.Storage;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Storage.StorageItem;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.CustomObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StorageMapper {

    @Mapping(source = "items", target = "idItems", qualifiedByName = "mapStorageItemListToIdList")
    @Mapping(source = "game.id", target = "idGame")
    StorageDTO toDTO(Storage storage);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "game", ignore = true)
    Storage toEntity(StorageDTO dto);


    @Named("mapStorageItemListToIdList")
    default List<Long> mapStorageItemListToIdList(List<StorageItem> items) {
        if (items == null) return null;
        return items.stream()
                .map(StorageItem::getId)
                .toList();
    }
}

