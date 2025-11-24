package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Template;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TemplateDTO.TemplateFieldDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.OptionList;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.Template;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.TemplateField;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TemplateFieldMapper {

    @Mapping(source = "templates", target = "idTemplates")
    @Mapping(source = "optionList.id", target = "idOptionList")
    TemplateFieldDTO toDTO(TemplateField templateField);

    @Mapping(target = "templates", ignore = true)
    @Mapping(target = "optionList", ignore = true)
    TemplateField toEntity(TemplateFieldDTO dto);


    default Long mapTemplateToId(Template template){
        return template != null ? template.getId() : null;
    }

    default Long mapOptionListToId(OptionList optionList){
        return optionList != null ? optionList.getId() : null;
    }
}
