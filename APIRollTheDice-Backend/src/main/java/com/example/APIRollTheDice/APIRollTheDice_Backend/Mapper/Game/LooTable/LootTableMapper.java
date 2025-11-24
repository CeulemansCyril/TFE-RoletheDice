package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.LooTable;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.LootTableDTO.LootElementDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.LootTableDTO.LootTableDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.LootTable.LootElement;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.LootTable.LootTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LootTableMapper {


    LootElementDTO toElementDTO(LootElement element);

    @Mapping(source = "gameBundle.id", target = "idGameBundle")
    LootTableDTO toDTO(LootTable lootTable);


    LootElement toElementEntity(LootElementDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "gameBundle", ignore = true)
    LootTable toEntity(LootTableDTO dto);
}
