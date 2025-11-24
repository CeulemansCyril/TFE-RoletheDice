package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.StorageInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Storage.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StorageInterface extends JpaRepository<Storage,Long> {
    Optional<Storage> findById(Long id);

    List<Storage> findAllByGame_id(Long id);
}
