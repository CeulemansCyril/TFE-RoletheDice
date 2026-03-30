package com.example.APIRollTheDice.Service.Game.Template;

import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.GameInterface.GameBundleInterface;
import com.example.APIRollTheDice.Interface.GameInterface.MoneyInterface.CurrencyInterface;
import com.example.APIRollTheDice.Interface.GameInterface.TemplateInterface.*;
import com.example.APIRollTheDice.Mapper.Game.Money.ValueMapper;
import com.example.APIRollTheDice.Mapper.Game.Template.*;
import com.example.APIRollTheDice.Model.DTO.GameDTO.MoneyDTO.ValueDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.*;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Value;
import com.example.APIRollTheDice.Model.Obj.Game.Template.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private final CustomObjectAttributeInterface customObjectAttributeInterface;
    private final CustomObjectAttributeMapper customObjectAttributeMapper;

    private final ValueMapper valueMapper;
    private final CurrencyInterface currencyInterface;

    private final GameBundleInterface gameBundleInterface;

    public TemplateService(TemplateFieldInterface templateFieldInterface, TemplateFieldMapper templateFieldMapper, TemplateInterface templateInterface,
                           TemplateMapper templateMapper, OptionListMapper optionListMapper, OptionListInterface optionListInterface,
                           CustomObjectInterface customObjectInterface, CustomObjectMapper customObjectMapper,
                           GameBundleInterface gameBundleInterface, CustomObjectAttributeInterface customObjectAttributeInterface
                           ,CustomObjectAttributeMapper customObjectAttributeMapper, ValueMapper valueMapper,
                           CurrencyInterface currencyInterface) {
        this.templateFieldInterface = templateFieldInterface;
        this.templateFieldMapper = templateFieldMapper;
        this.templateInterface = templateInterface;
        this.templateMapper = templateMapper;
        this.optionListMapper = optionListMapper;
        this.optionListInterface = optionListInterface;
        this.customObjectInterface = customObjectInterface;
        this.customObjectMapper = customObjectMapper;
        this.gameBundleInterface = gameBundleInterface;
        this.customObjectAttributeInterface = customObjectAttributeInterface;
        this.customObjectAttributeMapper = customObjectAttributeMapper;
        this.valueMapper = valueMapper;
        this.currencyInterface = currencyInterface;
    }

    public TemplateField CreateTemplateField(TemplateField templateField){
        return templateFieldInterface.save(templateField);
    }

    public List<TemplateField> CreateManyTemplateField(List<TemplateField> templateFields){
        return templateFieldInterface.saveAll(templateFields);
    }

    public TemplateField UpdateTemplateField(TemplateField templateField){
        TemplateField existing = templateFieldInterface.findById(templateField.getId()).orElseThrow(()-> new NotFoundException("TemplateField not found"));

        existing.setTemplate(templateField.getTemplate());
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
    public List<TemplateField> UpdateManyTemplateField(List<TemplateField> templateFields){
        List<TemplateField> updatedTemplateFields = new ArrayList<>();
        for (TemplateField temp : templateFields) {
            if (!templateFieldInterface.existsById(temp.getId())) {
                CreateTemplateField(temp);
            }else{
                TemplateField existing = templateFieldInterface.findById(temp.getId()).orElseThrow(() -> new NotFoundException("TemplateField not found"));
                existing.setTemplate(temp.getTemplate());
                existing.setLabel(temp.getLabel());
                existing.setMaxValue(temp.getMaxValue());
                existing.setMinValue(temp.getMinValue());
                existing.setOptionList(temp.getOptionList());
                existing.setPositionX(temp.getPositionX());
                existing.setPositionY(temp.getPositionY());
                existing.setType(temp.getType());
                existing.setRequired(temp.isRequired());
                updatedTemplateFields.add(existing);
            }
        }

        return templateFieldInterface.saveAll(updatedTemplateFields);
    }

    public void DeleteTemplateField (Long id){
        if (templateFieldInterface.existsById(id))templateFieldInterface.deleteById(id);
        else throw  new NotFoundException("TemplateField not found");
    }

    public List<TemplateField> GetAllTemplateFieldByTemplateId(Long id){
        return templateFieldInterface.findAllByTemplate_Id(id);
    }

    public TemplateFieldDTO TemplateFieldToDTO(TemplateField templateField){
        return templateFieldMapper.toDTO(templateField);
    }

    public TemplateField TemplateFieldDTOToEntity(TemplateFieldDTO templateFieldDTO){
        TemplateField templateField = templateFieldMapper.toEntity(templateFieldDTO);

        templateField.setTemplate( templateInterface.findById(templateFieldDTO.getIdTemplates()).orElseThrow(()-> new NotFoundException("Template not found")));

        Optional<OptionList> opt = optionListInterface.findById(templateFieldDTO.getIdOptionList());
        if (opt.isPresent()) {
            templateField.setOptionList(opt.get());
        } else {
            templateField.setOptionList(null);
        }

        return templateField;
    }
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public Template CreateTemplate(Template template){
        return templateInterface.save(template);
    }
    public List<Template> CreateManyTemplate(List<Template> templates){
        return templateInterface.saveAll(templates);
    }

    public Template UpdateTemplate(Template template){
        Template existing = templateInterface.findById(template.getId()).orElseThrow(()->new NotFoundException("Template not found"));

        existing.setName(template.getName());
        existing.setGameBundle(template.getGameBundle());

        existing.getTemplateFieldList().clear();
        existing.getTemplateFieldList().addAll(template.getTemplateFieldList());


        return templateInterface.save(existing);
    }

    public void DeleteTemplate (Long id){
        if(templateInterface.existsById(id))templateInterface.deleteById(id);
        else throw new NotFoundException("Template not found");
    }

    public Template GetTemplateById(Long id){
        return templateInterface.findById(id).orElseThrow(()-> new NotFoundException("Template not found"));
    }

    public List<Template> GetAllTemplateByGameBundleId(Long id){
        return templateInterface.findAllByGameBundle_Id(id);
    }

    public TemplateDTO TemplateToDTO(Template template){
        return  templateMapper.toDTO(template);
    }

    public Template TemplateDTOToEntity(TemplateDTO templateDTO){
        Template template = templateMapper.toEntity(templateDTO);
        if(templateDTO.getId() != null) {
            template.setTemplateFieldList(templateFieldInterface.findAllByTemplate_Id(templateDTO.getId()));


        }else {
            template.setTemplateFieldList(new ArrayList<>());

        }

        template.setGameBundle(gameBundleInterface.findById(templateDTO.getIdGameBundle()).orElseThrow(()-> new NotFoundException("GameBundle not found")));


        return template;
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public OptionList CreateOptionList(OptionList optionList){
        return optionListInterface.save(optionList);
    }
    public List<OptionList>CreateManyOptionList(List<OptionList> optionLists){
        return optionListInterface.saveAll(optionLists);
    }

    public OptionList UpdateOptionList(OptionList optionList){
        OptionList existing = optionListInterface.findById(optionList.getId()).orElseThrow(()-> new NotFoundException("OptionList not found"));

        existing.setTemplateField(optionList.getTemplateField());
        existing.setName(optionList.getName());
        existing.setOptions(optionList.getOptions());

        return optionListInterface.save(optionList);
    }

    public List<OptionList> UpdateManyOptionList(List<OptionList> optionLists){
        List<OptionList> updatedOptionLists = optionLists.stream().map(opt -> {
            OptionList existing = optionListInterface.findById(opt.getId()).orElseThrow(() -> new NotFoundException("OptionList not found"));
            existing.setTemplateField(opt.getTemplateField());
            existing.setName(opt.getName());
            existing.setOptions(opt.getOptions());
            return existing;
        }).toList();

        return optionListInterface.saveAll(updatedOptionLists);
    }

    public void DeleteManyOptionList(List<Long> ids){
        List<OptionList> optionListsToDelete = ids.stream().map(id -> optionListInterface.findById(id).orElseThrow(() -> new NotFoundException("OptionList not found"))).toList();
        optionListInterface.deleteAll(optionListsToDelete);
    }

    public void DeleteOptionList(Long id){
        if(optionListInterface.existsById(id)) optionListInterface.deleteById(id);
        else throw new NotFoundException("OptionList not found");
    }

    public OptionList GetOptionListById(Long id){
        return optionListInterface.findById(id).orElseThrow(()-> new NotFoundException("OptionList not found"));
    }

    public OptionList GetOptionListByTemplateFieldId(Long id){
        return optionListInterface.findByTemplateField_Id(id).orElseThrow(()-> new NotFoundException("OptionList not found"));
    }
    public List<OptionList> GetAllOptionListByGameBundleId(Long id){
        return optionListInterface.findByGameBundle_Id(id).stream().map(opt -> opt.orElseThrow(()-> new NotFoundException("OptionList not found"))).toList();
    }

    public OptionListDTO OptionListToDTO(OptionList optionList){
        return optionListMapper.toDTO(optionList);
    }

    public OptionList OptionDTOToEntity(OptionListDTO optionListDTO){
        OptionList optionList = optionListMapper.toEntity(optionListDTO);

        optionList.setTemplateField(templateFieldInterface.findById(optionListDTO.getIdTemplateField()).orElseThrow(()-> new NotFoundException("TemplateField not found")));

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
        existing.getAttributes().clear();

        for (CustomObjectAttribute attr : customObject.getAttributes()) {
            attr.setCustomObject(existing);
            existing.getAttributes().add(attr);
        }

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
        return customObjectInterface.findAllByGameBundles_id(id);
    }

    public CustomObjectDTO CustomObjectToDTO(CustomObject customObject){
        CustomObjectDTO customObjectDTO = customObjectMapper.toDTO(customObject);
        List<CustomObjectAttributeDTO> dtos = customObject.getAttributes().stream().map( this::CustomObjectAttributeToDTO ).toList();
        customObjectDTO.setAttributes(dtos);
        customObjectDTO.setPrice(CustomObjectPriceToDTO(customObject.getPrice()));
        return customObjectDTO;
    }

    public CustomObject CustomObjectDTOToEntity(CustomObjectDTO customObjectDTO){
        CustomObject customObject = customObjectMapper.toEntity(customObjectDTO);
        customObject.setGameBundles(gameBundleInterface.findById(customObjectDTO.getIdGameBundles()).orElseThrow(()-> new NotFoundException("GameBundle not found")));
        customObject.setTemplate(templateInterface.findById(customObjectDTO.getIdTemplate()).orElseThrow(()-> new NotFoundException("Template not found")));
        customObject.setAttributes(customObjectAttributeInterface.findByCustomObjectId(customObjectDTO.getId()));
        customObject.setPrice(CustomObjectDTOPriceToValue(customObjectDTO.getPrice()));


        return customObject ; 
    }

    public Value CustomObjectDTOPriceToValue(ValueDTO valueDTO){
        Value value = valueMapper.toEntity(valueDTO);
        value.setCurrency(currencyInterface.getReferenceById(valueDTO.getCurrencyId()));
        return value;
    }

    public ValueDTO CustomObjectPriceToDTO(Value value){
        return valueMapper.toDTO(value);
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public CustomObjectAttribute CreateCustomObjectAttribute(CustomObjectAttribute customObjectAttribute){
      return customObjectAttributeInterface.save(customObjectAttribute);
    }

    public List<CustomObjectAttribute> CreateManyCustomObjectAttribute( List<CustomObjectAttribute> attributes){
        return customObjectAttributeInterface.saveAll(attributes);
    }

    public CustomObjectAttribute UpdateCustomObjectAttribute (CustomObjectAttribute customObjectAttribute){
        CustomObjectAttribute existing = customObjectAttributeInterface.findById(customObjectAttribute.getId()).orElseThrow(()-> new NotFoundException("GameBundle not found"));

        existing.setValue(customObjectAttribute.getValue());
        existing.setType(customObjectAttribute.getType());

        return customObjectAttributeInterface.save(existing);

    }

    public List<CustomObjectAttribute>UpdateManyCustomObjectAttribute(List<CustomObjectAttribute> attributes)
    {
        List<CustomObjectAttribute> updatedCustomObjectAttribute = attributes.stream().map(opt -> {
            CustomObjectAttribute existing = customObjectAttributeInterface.findById(opt.getId()).orElseThrow(() -> new NotFoundException("CustomObjectAttribute not found"));
            existing.setType(opt.getType());
            existing.setValue(opt.getValue());
            return existing;
        }).toList();

        return updatedCustomObjectAttribute;
    }

    public void DeleteCustomObjectAttribute(Long id){
        if(customObjectAttributeInterface.existsById(id))customObjectAttributeInterface.deleteById(id);
        else throw  new NotFoundException("customObjectAttributeInterface not found");
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<CustomObjectAttribute> CreateManyCustomObjectAttributeByCustomObjectId(List<CustomObjectAttribute> attributes, Long id){
        CustomObject customObject = customObjectInterface.findById(id).orElseThrow(()-> new NotFoundException("CustomObject not found"));
        for (CustomObjectAttribute attr : attributes) {
            attr.setCustomObject(customObject);
        }
        return customObjectAttributeInterface.saveAll(attributes);
    }

    public List<CustomObjectAttribute> UpdateManyCustomObjectAttributeByCustomObjectId(List<CustomObjectAttribute> attributes, Long id){
        CustomObject customObject = customObjectInterface.findById(id).orElseThrow(()-> new NotFoundException("CustomObject not found"));
        List<CustomObjectAttribute> updatedAttributes = new ArrayList<>();
        for (CustomObjectAttribute attr : attributes) {
            if (!customObjectAttributeInterface.existsById(attr.getId())) {
                attr.setCustomObject(customObject);
                updatedAttributes.add(attr);
            }else{
                CustomObjectAttribute existing = customObjectAttributeInterface.findById(attr.getId()).orElseThrow(() -> new NotFoundException("CustomObjectAttribute not found"));
                existing.setType(attr.getType());
                existing.setValue(attr.getValue());
                updatedAttributes.add(existing);
            }
        }
        return customObjectAttributeInterface.saveAll(updatedAttributes);
    }

    public void DeleteManyCustomObjectAttributeByCustomObjectId(List<Long> ids){
        List<CustomObjectAttribute> attributesToDelete = ids.stream().map(id -> customObjectAttributeInterface.findById(id).orElseThrow(() -> new NotFoundException("CustomObjectAttribute not found"))).toList();
        customObjectAttributeInterface.deleteAll(attributesToDelete);
    }

    public List<CustomObjectAttribute> GetAllCustomObjectAttributeByCustomObjectId(Long id){
        return customObjectAttributeInterface.findByCustomObjectId(id);
    }

    public CustomObjectAttribute CustomObjectAttributeDTOtoEntity(CustomObjectAttributeDTO customObjectAttributeDTO){
        CustomObjectAttribute customObjectAttribute = customObjectAttributeMapper.toEntity(customObjectAttributeDTO);
        customObjectAttribute.setCustomObject(customObjectInterface.findById(customObjectAttributeDTO.getIdCustomObject()).orElseThrow(()->new NotFoundException("CustomObjectAttribute not found")));
        customObjectAttribute.setTemplateField(templateFieldInterface.findById(customObjectAttributeDTO.getIdTemplateField()).orElseThrow(()->new NotFoundException("CustomObjectAttribute not found")));
        return customObjectAttribute;
    }

    public CustomObjectAttributeDTO CustomObjectAttributeToDTO(CustomObjectAttribute customObjectAttribute){
        return customObjectAttributeMapper.toDTO(customObjectAttribute);
    }

}
