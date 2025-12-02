package com.example.APIRollTheDice.Interface.GameInterface.TokenInterface;

import com.example.APIRollTheDice.Model.Obj.Game.Token.TokenPlaced;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenPlacedInterface extends JpaRepository<TokenPlaced,Long> {
    Optional<TokenPlaced> findById(Long id);

    List<TokenPlaced> findAllByLayoutId(Long id);
}
