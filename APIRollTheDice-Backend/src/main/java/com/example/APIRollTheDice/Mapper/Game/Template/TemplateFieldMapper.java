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

    @Mapping(source = "templates", target = "idTemplates", qualifiedByName = "mapTemplatesToIds")
    @Mapping(source = "optionList.id", target = "idOptionList")
    TemplateFieldDTO toDTO(TemplateField templateField);

    @Mapping(target = "templates", ignore = true)
    @Mapping(target = "optionList", ignore = true)
    TemplateField toEntity(TemplateFieldDTO dto);

    @Named("mapTemplatesToIds")
    default List<Long> mapTemplatesToIds(List<Template> templates) {
        if (templates == null) {
            return null;
        }
        return templates.stream()
                .map(Template::getId)
                .toList();
    }

}
