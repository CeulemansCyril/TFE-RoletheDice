package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Template;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TemplateDTO.OptionListDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.OptionList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OptionListMapper {

    @Mapping(source = "gameBundle.id", target = "idGameBundles")
    OptionListDTO toDTO(OptionList optionList);

    @Mapping(target = "gameBundle", ignore = true)
    OptionList toEntity(OptionListDTO optionListDTO);

    default Long mapGameBundleToId(GameBundle gameBundle) {
        return gameBundle != null ? gameBundle.getId() : null;
    }
}
