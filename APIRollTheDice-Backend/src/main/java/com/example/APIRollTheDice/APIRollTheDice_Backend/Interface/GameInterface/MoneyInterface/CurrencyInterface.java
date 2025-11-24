package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.MoneyInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Money.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CurrencyInterface extends JpaRepository<Currency,Long> {
    Optional<Currency> findById(Long id);

    List<Currency> findAllByGameBundles_id(Long id);
}
