package com.example.APIRollTheDice.Mapper.Game.Template;

import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.OptionListDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Template.OptionList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OptionListMapper {

    @Mapping(source = "templateField.id", target = "idTemplateField")
    OptionListDTO toDTO(OptionList optionList);

    @Mapping(target = "templateField", ignore = true)
    OptionList toEntity(OptionListDTO optionListDTO);


}
