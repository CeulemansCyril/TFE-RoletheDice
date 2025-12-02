package com.example.APIRollTheDice.Service.Game.Money;

import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.GameInterface.MoneyInterface.CurrencyInterface;
import com.example.APIRollTheDice.Mapper.Game.Money.CurrencyMapper;
import com.example.APIRollTheDice.Model.DTO.GameDTO.MoneyDTO.CurrencyDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Currency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    private final CurrencyInterface currencyInterface;
    private final CurrencyMapper currencyMapper;


    public CurrencyService(CurrencyInterface currencyInterface, CurrencyMapper currencyMapper) {
        this.currencyInterface = currencyInterface;
        this.currencyMapper = currencyMapper;
    }

    public Currency CreateCurrency(Currency currency){
        return currencyInterface.save(currency);
    }

    public Currency UpdateCurrency(Currency currency){
        Currency existing = currencyInterface.findById(currency.getId()).orElseThrow(()-> new NotFoundException("Currency not found"));

        existing.setGameBundles(currency.getGameBundles());

        return currencyInterface.save(existing);
    }

    public void DeleteCurrency(Long id){
        if(currencyInterface.existsById(id)) currencyInterface.deleteById(id);
        else throw new NotFoundException("Currency not found");
    }

    public List<Currency> GetAllCurrencyByGameBundle(Long idGameBundle){
        return currencyInterface.findAllByGameBundles_id(idGameBundle);
    }


    public CurrencyDTO toDTO(Currency currency){
        return currencyMapper.toDTO(currency);
    }

    public Currency toEntity(CurrencyDTO currencyDTO){
        return currencyMapper.toEntity(currencyDTO);
    }

}
