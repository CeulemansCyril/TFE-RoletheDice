package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.LootTableInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.LootTable.LootTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LootTableInterface extends JpaRepository<LootTable,Long> {
    Optional<LootTable> findById(Long id);
}
