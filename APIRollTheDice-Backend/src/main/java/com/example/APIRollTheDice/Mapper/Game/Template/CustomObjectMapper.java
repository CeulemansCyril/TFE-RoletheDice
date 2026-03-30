package com.example.APIRollTheDice.Mapper.Game.Template;

import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.CustomObjectAttributeDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.CustomObjectDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Template.CustomObject;
import com.example.APIRollTheDice.Model.Obj.Game.Template.CustomObjectAttribute;
import com.example.APIRollTheDice.Model.Obj.Game.Template.TemplateField;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomObjectMapper {
    @Mapping(source = "template.id", target = "idTemplate")
    @Mapping(source = "gameBundles.id", target = "idGameBundles")
    @Mapping(target = "attributes", ignore = true )
    @Mapping(target = "price", ignore = true)
    CustomObjectDTO toDTO(CustomObject customObject);

    @Mapping(target = "template", ignore = true)
    @Mapping(target = "gameBundles", ignore = true)
    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "price", ignore = true)
    CustomObject toEntity(CustomObjectDTO customObjectDTO);



}
