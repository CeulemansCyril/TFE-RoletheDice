package com.example.APIRollTheDice.Mapper.Game.Template;

import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.TemplateDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Template.Template;
import com.example.APIRollTheDice.Model.Obj.Game.Template.TemplateField;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TemplateMapper {

    @Mapping(source = "gameBundle.id", target = "idGameBundle")
    @Mapping(source = "templateFieldList", target = "idTemplateFieldList", qualifiedByName = "templateFieldsToIds")
    TemplateDTO toDTO(Template template);

    @Mapping(target = "gameBundle", ignore = true)
    @Mapping(target = "templateFieldList", ignore = true)
    Template toEntity(TemplateDTO templateDTO);

    @Named("templateFieldsToIds")
    default List<Long> mapTemplateFieldsToIds(List<TemplateField> list) {
        return list == null ? null : list.stream().map(TemplateField::getId).toList();
    }
}
