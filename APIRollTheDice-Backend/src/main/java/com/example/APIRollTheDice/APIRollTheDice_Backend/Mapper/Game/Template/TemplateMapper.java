package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Template;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TemplateDTO.TemplateDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.Template;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.TemplateField;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TemplateMapper {

    @Mapping(source = "gameBundle.id", target = "idGameBundles")
    @Mapping(source = "templateFieldList", target = "idTemplateFieldList")
    TemplateDTO toDTO(Template template);

    @Mapping(target = "gameBundle", ignore = true)
    @Mapping(target = "templateFieldList", ignore = true)
    Template toEntity(TemplateDTO templateDTO);

    default Long mapGameBundleToId(GameBundle gameBundle){
        return gameBundle != null ? gameBundle.getId() : null;
    }

    // MapStruct appliquera cette méthode automatiquement
    default Long mapTemplateFieldToId(TemplateField field){
        return field != null ? field.getId() : null ;
    }
}
