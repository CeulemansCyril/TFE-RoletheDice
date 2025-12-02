package com.example.APIRollTheDice.Mapper.Game.Money;

import com.example.APIRollTheDice.Model.DTO.GameDTO.MoneyDTO.CurrencyDTO;
import com.example.APIRollTheDice.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

    @Mapping(source = "gameBundles", target = "idGameBundle", qualifiedByName = "mapGameBundleToId")
    CurrencyDTO toDTO(Currency currency);

    @Mapping(target = "gameBundles", ignore = true)
    Currency toEntity(CurrencyDTO dto);

    @Named("mapGameBundleToId")
    default List<Long> mapGameBundleToId(List<GameBundle> gameBundle) {
        if(gameBundle == null) return null;
        return gameBundle.stream().map(GameBundle::getId).toList();

    }

}
