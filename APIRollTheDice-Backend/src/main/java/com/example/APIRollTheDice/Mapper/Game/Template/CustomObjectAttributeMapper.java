package com.example.APIRollTheDice.Mapper.Game.Template;

import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.CustomObjectAttributeDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Template.CustomObject;
import com.example.APIRollTheDice.Model.Obj.Game.Template.CustomObjectAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomObjectAttributeMapper {
    @Mapping(source = "customObject.id", target = "idCustomObject")
    @Mapping(source = "templateField.id", target = "idTemplateField")
    CustomObjectAttributeDTO toDTO(CustomObjectAttribute customObjectAttribute);

    @Mapping(target = "customObject", ignore = true)
    @Mapping(target = "templateField", ignore = true)
    CustomObjectAttribute toEntity(CustomObjectAttributeDTO customObjectAttributeDTO);

}
