package com.example.APIRollTheDice.Service.Game.Money;

import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.GameInterface.GameBundleInterface;
import com.example.APIRollTheDice.Interface.GameInterface.MoneyInterface.CurrencyInterface;
import com.example.APIRollTheDice.Mapper.Game.Money.CurrencyMapper;
import com.example.APIRollTheDice.Model.DTO.GameDTO.MoneyDTO.CurrencyDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Currency;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {
    private final CurrencyInterface currencyInterface;
    private final CurrencyMapper currencyMapper;

    private final GameBundleInterface gameBundleInterface;

    public CurrencyService(CurrencyInterface currencyInterface, CurrencyMapper currencyMapper,GameBundleInterface gameBundleInterface) {
        this.currencyInterface = currencyInterface;
        this.currencyMapper = currencyMapper;
        this.gameBundleInterface =gameBundleInterface;
    }

    public Currency CreateCurrency(Currency currency){
        return currencyInterface.save(currency);
    }
    public List<Currency> CreateManyCurrency(List<Currency> currencies) { return  currencyInterface.saveAll(currencies);}

    public Currency UpdateCurrency(Currency currency){
        Currency existing = currencyInterface.findById(currency.getId()).orElseThrow(()-> new NotFoundException("Currency not found"));

        existing.setCode(currency.getCode());
        existing.setName(currency.getName());
        existing.setSymbol(currency.getSymbol());
        existing.setBaseUnit(currency.getBaseUnit());

        return currencyInterface.save(existing);
    }

    public  List<Currency> UpdateManyCurrency(List<Currency> currencies){
        List<Currency> currencyListUpdate = new ArrayList<>();
        for(Currency currency : currencies){
            Currency existing = currencyInterface.findById(currency.getId()).orElseThrow(()-> new NotFoundException("Currency not found"));

            existing.setCode(currency.getCode());
            existing.setName(currency.getName());
            existing.setSymbol(currency.getSymbol());
            existing.setBaseUnit(currency.getBaseUnit());

            currencyListUpdate.add(currencyInterface.save(existing));
        }

        return  currencyListUpdate;
    }

    public void DeleteCurrency(Long id){
        if(currencyInterface.existsById(id)) currencyInterface.deleteById(id);
        else throw new NotFoundException("Currency not found");
    }

    public List<Currency> GetAllCurrencyByGameBundle(Long idGameBundle){
        return currencyInterface.findAllByGameBundle_id(idGameBundle);
    }


    public CurrencyDTO toDTO(Currency currency){
        return currencyMapper.toDTO(currency);
    }

    public Currency toEntity(CurrencyDTO currencyDTO){
        Currency currency = currencyMapper.toEntity(currencyDTO);
        currency.setGameBundle(gameBundleInterface.findById(currencyDTO.getIdGameBundle()).orElseThrow(()->new NotFoundException("GameBundle not found in  Currency toEntity")));

        return currency ;
    }



}
