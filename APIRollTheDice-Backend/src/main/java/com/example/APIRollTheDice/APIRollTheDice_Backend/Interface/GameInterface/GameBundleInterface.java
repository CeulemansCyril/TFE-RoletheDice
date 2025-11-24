package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameBundleInterface extends JpaRepository<GameBundle,Long> {
    Optional<GameBundle> findById(Long id);
}
