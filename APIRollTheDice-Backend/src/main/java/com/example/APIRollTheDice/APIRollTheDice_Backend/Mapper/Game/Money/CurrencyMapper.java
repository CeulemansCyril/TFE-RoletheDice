package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Money;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.MoneyDTO.CurrencyDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Money.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    @Mapping(source = "gameBundle.id", target = "idGameBundle")
    CurrencyDTO toDTO(Currency currency);

    @Mapping(target = "gameBundle", ignore = true)
    Currency toEntity(CurrencyDTO dto);

}
