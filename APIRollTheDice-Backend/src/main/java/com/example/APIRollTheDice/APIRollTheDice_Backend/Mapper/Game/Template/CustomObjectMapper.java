package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Template;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TemplateDTO.CustomObjectDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.CustomObject;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.Template;
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
