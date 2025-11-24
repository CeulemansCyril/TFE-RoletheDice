package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.StorageInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Storage.StorageItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StorageItemInterface extends JpaRepository<StorageItem,Long> {
    Optional<StorageItem> findById(Long id);
    List<StorageItem> findAllByStorage_id(Long id);
}
