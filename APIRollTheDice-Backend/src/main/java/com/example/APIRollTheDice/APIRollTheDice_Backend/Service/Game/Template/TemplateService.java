package com.example.APIRollTheDice.APIRollTheDice_Backend.Service.Game.Template;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.GameBundleInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TemplateInterface.CustomObjectInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TemplateInterface.OptionListInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TemplateInterface.TemplateFieldInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TemplateInterface.TemplateInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Template.CustomObjectMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Template.OptionListMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Template.TemplateFieldMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Template.TemplateMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TemplateDTO.CustomObjectDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TemplateDTO.OptionListDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TemplateDTO.TemplateDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TemplateDTO.TemplateFieldDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.CustomObject;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.OptionList;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.Template;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template.TemplateField;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {
    private final TemplateFieldInterface templateFieldInterface;
    private final TemplateFieldMapper templateFieldMapper;

    private final TemplateInterface templateInterface;
    private final TemplateMapper templateMapper;

    private final OptionListMapper optionListMapper;
    private final OptionListInterface optionListInterface;

    private final CustomObjectInterface customObjectInterface;
    private final CustomObjectMapper customObjectMapper;

    private final GameBundleInterface gameBundleInterface;

    public TemplateService(TemplateFieldInterface templateFieldInterface, TemplateFieldMapper templateFieldMapper, TemplateInterface templateInterface, TemplateMapper templateMapper, OptionListMapper optionListMapper, OptionListInterface optionListInterface, CustomObjectInterface customObjectInterface, CustomObjectMapper customObjectMapper, GameBundleInterface gameBundleInterface) {
        this.templateFieldInterface = templateFieldInterface;
        this.templateFieldMapper = templateFieldMapper;
        this.templateInterface = templateInterface;
        this.templateMapper = templateMapper;
        this.optionListMapper = optionListMapper;
        this.optionListInterface = optionListInterface;
        this.customObjectInterface = customObjectInterface;
        this.customObjectMapper = customObjectMapper;
        this.gameBundleInterface = gameBundleInterface;
    }

    public TemplateField CreateTemplateField(TemplateField templateField){
        return templateFieldInterface.save(templateField);
    }

    public TemplateField UpdateTemplateField(TemplateField templateField){
        TemplateField existing = templateFieldInterface.findById(templateField.getId()).orElseThrow(()-> new NotFoundException("TemplateField not found"));

        existing.setTemplate(templateField.getTemplates());
        existing.setLabel(templateField.getLabel());
        existing.setMaxValue(templateField.getMaxValue());
        existing.setMinValue(templateField.getMinValue());
        existing.setOptionList(templateField.getOptionList());
        existing.setPositionX(templateField.getPositionX());
        existing.setPositionY(templateField.getPositionY());
        existing.setType(templateField.getType());
        existing.setRequired(templateField.isRequired());

        return templateFieldInterface.save(templateField);
    }

    public void DeleteTemplateField (Long id){
        if (templateFieldInterface.existsById(id))templateFieldInterface.deleteById(id);
        else throw  new NotFoundException("TemplateField not found");
    }

    public List<TemplateField> GetAllTemplateFieldByTemplateId(Long id){
        return templateFieldInterface.findAllByTemplates_Id(id);
    }

    public TemplateFieldDTO TemplateFieldToDTO(TemplateField templateField){
        return templateFieldMapper.toDTO(templateField);
    }

    public TemplateField TemplateFieldDTOToEntity(TemplateFieldDTO templateFieldDTO){
        TemplateField templateField = templateFieldMapper.toEntity(templateFieldDTO);

        templateField.setTemplate(templateInterface.findAllById(templateFieldDTO.getIdTemplates()));
        templateField.setOptionList(optionListInterface.findById(templateFieldDTO.getIdOptionList()).get());

        return templateField;
    }
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public Template CreateTemplate(Template template){
        return templateInterface.save(template);
    }

    public Template UpdateTemplate(Template template){
        Template existing = templateInterface.findById(template.getId()).orElseThrow(()->new NotFoundException("Template not found"));

        existing.setName(template.getName());
        existing.setGameBundle(template.getGameBundle());
        existing.setTemplateFieldList(template.getTemplateFieldList());

        return templateInterface.save(existing);
    }

    public void DeleteTemplate (Long id){
        if(templateInterface.existsById(id))templateInterface.deleteById(id);
        else throw new NotFoundException("Template not found");
    }

    public TemplateDTO TemplateToDTO(Template template){
        return  templateMapper.toDTO(template);
    }

    public Template TemplateDTOToEntity(TemplateDTO templateDTO){
        Template template = templateMapper.toEntity(templateDTO);
        template.setTemplateFieldList(templateFieldInterface.findAllByTemplates_Id(templateDTO.getId()));
        template.setGameBundle(gameBundleInterface.findById(templateDTO.getIdGameBundle()).orElseThrow(()-> new NotFoundException("GameBundle not found")));

        return template;
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public OptionList CreateOptionList(OptionList optionList){
        return optionListInterface.save(optionList);
    }

    public OptionList UpdateOptionList(OptionList optionList){
        OptionList existing = optionListInterface.findById(optionList.getId()).orElseThrow(()-> new NotFoundException("OptionList not found"));

        existing.setGameBundle(optionList.getGameBundle());
        existing.setName(optionList.getName());
        existing.setOptions(optionList.getOptions());

        return optionListInterface.save(optionList);
    }

    public void DeleteOptionList(Long id){
        if(optionListInterface.existsById(id)) optionListInterface.deleteById(id);
        else throw new NotFoundException("OptionList not found");
    }

    public OptionListDTO OptionListToDTO(OptionList optionList){
        return optionListMapper.toDTO(optionList);
    }

    public OptionList OptionDTOToEntity(OptionListDTO optionListDTO){
        OptionList optionList = optionListMapper.toEntity(optionListDTO);

        optionList.setGameBundle(gameBundleInterface.findById(optionListDTO.getIdGameBundle()).orElseThrow(()-> new NotFoundException("GameBundle not found")));

        return optionList;
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public CustomObject CreateCustomObject (CustomObject customObject){
        return customObjectInterface.save(customObject);
    }

    public CustomObject UpdateCustomObject (CustomObject customObject){
        CustomObject existing = customObjectInterface.findById(customObject.getId()).orElseThrow(()-> new NotFoundException("CustomObject not found"));

        existing.setTemplate(customObject.getTemplate());
        existing.setName(customObject.getName());
        existing.setPrice(customObject.getPrice());
        existing.setFieldValues(customObject.getFieldValues());
        existing.setCanBeInInventory(customObject.isCanBeInInventory());
        existing.setGameBundles(customObject.getGameBundles());

        return customObjectInterface.save(existing);
    }

    public void DeleteCustomObject (Long id){
        if(customObjectInterface.existsById(id))customObjectInterface.deleteById(id);
        else throw  new NotFoundException("CustomObject not found");
    }

    public CustomObject GetCustomObjectById(Long id){
        return customObjectInterface.findById(id).orElseThrow(()->new NotFoundException("CustomObject not found"));
    }

    public List<CustomObject> GetAllCustomObjectByGameBundleId(Long id){
        return customObjectInterface.findAllByGameBundle_id(id);
    }

    public CustomObjectDTO CustomObjectToDTO(CustomObject customObject){
        return customObjectMapper.toDTO(customObject);
    }

    public CustomObject CustomObjectDTOToEntity(CustomObjectDTO customObjectDTO){
        CustomObject customObject = customObjectMapper.toEntity(customObjectDTO);
        customObject.setGameBundles(gameBundleInterface.findById(customObjectDTO.getIdGameBundles()).orElseThrow(()-> new NotFoundException("GameBundle not found")));
        customObject.setTemplate(templateInterface.findById(customObjectDTO.getIdTemplate()).orElseThrow(()-> new NotFoundException("Template not found")));

        return customObject ; 
    }


}
