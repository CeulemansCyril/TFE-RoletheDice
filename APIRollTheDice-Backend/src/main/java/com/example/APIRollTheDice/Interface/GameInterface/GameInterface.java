package com.example.APIRollTheDice.Interface.GameInterface;

import com.example.APIRollTheDice.Model.Obj.Game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameInterface extends JpaRepository<Game,Long> {
    Optional<Game> findById(Long id);
}
