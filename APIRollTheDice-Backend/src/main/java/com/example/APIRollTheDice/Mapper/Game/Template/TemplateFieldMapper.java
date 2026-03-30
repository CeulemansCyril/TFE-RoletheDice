package com.example.APIRollTheDice.Mapper.Game.Template;

import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.TemplateFieldDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Template.Template;
import com.example.APIRollTheDice.Model.Obj.Game.Template.TemplateField;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TemplateFieldMapper {

    @Mapping(source = "template.id", target = "idTemplates")
    @Mapping(source = "optionList.id", target = "idOptionList")
    TemplateFieldDTO toDTO(TemplateField templateField);

    @Mapping(target = "template", ignore = true)
    @Mapping(target = "optionList", ignore = true)
    TemplateField toEntity(TemplateFieldDTO dto);



}
