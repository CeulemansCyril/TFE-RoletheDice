package com.example.APIRollTheDice.Mapper.Game.Money;

import com.example.APIRollTheDice.Model.DTO.GameDTO.MoneyDTO.ValueDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Value;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ValueMapper {
    @Mapping(source = "currency.id", target = "currencyId")
    ValueDTO toDTO(Value value);

    @Mapping(target = "currency", ignore = true)
    Value toEntity(ValueDTO valueDTO);
}
