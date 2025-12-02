package com.example.APIRollTheDice.Mapper.Game.LooTable;

import com.example.APIRollTheDice.Model.DTO.GameDTO.LootTableDTO.LootElementDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.LootTableDTO.LootTableDTO;
import com.example.APIRollTheDice.Model.Obj.Game.LootTable.LootElement;
import com.example.APIRollTheDice.Model.Obj.Game.LootTable.LootTable;
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
