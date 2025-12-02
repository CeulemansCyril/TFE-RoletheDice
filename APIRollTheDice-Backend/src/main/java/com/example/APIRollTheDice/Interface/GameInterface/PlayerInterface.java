package com.example.APIRollTheDice.Interface.GameInterface;

import com.example.APIRollTheDice.Model.Obj.Game.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerInterface extends JpaRepository<Player,Long> {
    Optional<Player> findById(Long id);

    List<Player> findByUser_Id(Long userId);
    List<Player> findByGame_Id(Long gameId);
}
