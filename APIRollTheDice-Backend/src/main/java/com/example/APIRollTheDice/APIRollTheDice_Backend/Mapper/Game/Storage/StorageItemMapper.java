package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Storage;


import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.Storage.StorageItemDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Storage.Storage;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Storage.StorageItem;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.CustomObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StorageItemMapper {

    @Mapping(source = "customObject.id", target = "idCustomObject")
    @Mapping(source = "storage.id", target = "idStorage")
    StorageItemDTO toDTO(StorageItem item);

    @Mapping(target = "customObject", ignore = true)
    @Mapping(target = "storage", ignore = true)
    StorageItem toEntity(StorageItemDTO dto);

    default Long mapCustomObjectToId(CustomObject customObject) {
        return customObject != null ? customObject.getId() : null;
    }

    default Long mapStorageToId(Storage storage) {
        return storage != null ? storage.getId() : null;
    }
}

