package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerInterface extends JpaRepository<Player,Long> {
    Optional<Player> findById(Long id);
}
