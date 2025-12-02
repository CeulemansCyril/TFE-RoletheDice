package com.example.APIRollTheDice.Mapper.Game.Storage;


import com.example.APIRollTheDice.Model.DTO.GameDTO.Storage.StorageItemDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Storage.StorageItem;
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


}

