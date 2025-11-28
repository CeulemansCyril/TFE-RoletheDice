package com.example.APIRollTheDice.APIRollTheDice_Backend.Service.Game.LootTable;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.GameBundleInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.LootTableInterface.LootTableInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.LooTable.LootTableMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.LootTableDTO.LootElementDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.LootTableDTO.LootTableDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.LootTable.LootElement;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.LootTable.LootTable;
import org.springframework.stereotype.Service;

@Service
public class LootTableService {
    private final LootTableMapper lootTableMapper;
    private final LootTableInterface lootTableInterface;

    private final GameBundleInterface gameBundleInterface;

    public LootTableService(LootTableMapper lootTableMapper, LootTableInterface lootTableInterface,GameBundleInterface gameBundleInterface) {
        this.lootTableMapper = lootTableMapper;
        this.lootTableInterface = lootTableInterface;
        this.gameBundleInterface =gameBundleInterface;
    }

    public LootTable CreateLootTable(LootTable lootTable){
       return lootTableInterface.save(lootTable);
    }

    public LootTable UpdateLooTable(LootTable lootTable){
        LootTable existing = lootTableInterface.findById(lootTable.getId()).orElseThrow(() ->  new  NotFoundException("LootTable not found"));

        existing.setName(lootTable.getName());
        existing.setLootElements(lootTable.getLootElements());

        return lootTableInterface.save(existing);
    }

    public void DeleteLootTable(Long id){
        if(lootTableInterface.existsById(id)) lootTableInterface.deleteById(id);
        else throw new  NotFoundException("LootTable not found");
    }




    public LootElementDTO LootElmentToDTO(LootElement lootElement){
        return  lootTableMapper.toElementDTO(lootElement);
    }

    public LootElement LootElementDTOToEntity(LootElementDTO lootElementDTO){
        return lootTableMapper.toElementEntity(lootElementDTO);
    }

    public LootTableDTO LootTableToDTO(LootTable lootTable){
        return  lootTableMapper.toDTO(lootTable);
    }

    public LootTable LootTableDTOToEntity(LootTableDTO lootTableDTO){
        LootTable lootTable = lootTableMapper.toEntity(lootTableDTO);
        lootTable.setGameBundle(gameBundleInterface.findById(lootTableDTO.getIdGameBundle()).orElseThrow(() -> new NotFoundException("GameBundle not found")));
        return lootTable;
    }
}
