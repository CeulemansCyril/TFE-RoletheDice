package com.example.APIRollTheDice.Mapper.Game.Template;

import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.CustomObjectDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Template.CustomObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomObjectMapper {
    @Mapping(source = "template.id", target = "idTemplate")
    @Mapping(source = "gameBundles.id", target = "idGameBundles")
    CustomObjectDTO toDTO(CustomObject customObject);

    @Mapping(target = "template", ignore = true)
    @Mapping(target = "gameBundles", ignore = true)
    CustomObject toEntity(CustomObjectDTO customObjectDTO);


}
