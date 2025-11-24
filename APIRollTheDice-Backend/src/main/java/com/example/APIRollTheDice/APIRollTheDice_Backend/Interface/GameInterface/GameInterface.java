package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameInterface extends JpaRepository<Game,Long> {
    Optional<Game> findById(Long id);
}
