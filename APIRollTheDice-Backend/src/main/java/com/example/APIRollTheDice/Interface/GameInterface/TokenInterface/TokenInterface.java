package com.example.APIRollTheDice.Interface.GameInterface.TokenInterface;

import com.example.APIRollTheDice.Model.Obj.Game.Token.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenInterface extends JpaRepository<Token,Long> {
    Optional<Token> findById(Long id);

    List<Token> findAllByGameBundle_id(Long id);
}

