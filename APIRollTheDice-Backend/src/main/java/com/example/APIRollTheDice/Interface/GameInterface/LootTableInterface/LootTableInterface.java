package com.example.APIRollTheDice.Interface.GameInterface.LootTableInterface;

import com.example.APIRollTheDice.Model.Obj.Game.LootTable.LootTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LootTableInterface extends JpaRepository<LootTable,Long> {
    Optional<LootTable> findById(Long id);

    List<LootTable> findAllByGameBundle_id(Long gameBundleId);
}
