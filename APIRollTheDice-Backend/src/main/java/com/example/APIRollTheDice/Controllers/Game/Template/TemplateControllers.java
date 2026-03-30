package com.example.APIRollTheDice.Controllers.Game.Template;

import com.example.APIRollTheDice.Enum.CreatedItemType;

import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.*;
import com.example.APIRollTheDice.Model.DTO.ReponseDTO.TemplateFieldResponseDTO;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Template.*;
import com.example.APIRollTheDice.Service.Game.Template.TemplateService;
import com.example.APIRollTheDice.Service.User.UserCreationContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/template")
public class TemplateControllers {
    private final UserCreationContentService userCreationContentService;
    private final TemplateService templateService;

    public TemplateControllers(UserCreationContentService userCreationContentService, TemplateService templateService) {
        this.userCreationContentService = userCreationContentService;
        this.templateService = templateService;
    }

    // ------------------- CustomObject  -------------------------
    @PostMapping("/createCustomObject/{userId}")
    public ResponseEntity<CustomObjectDTO>CreateCustomObject(@PathVariable Long userId, @RequestBody CustomObjectDTO customObjectDTO){
        CustomObject customObject = templateService.CustomObjectDTOToEntity(customObjectDTO);
        customObject.setId(null);
         customObject = templateService.CreateCustomObject(customObject);

        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setUserId(userId);
        userCreationContentDTO.setCreatedItemId(customObject.getId());
        userCreationContentDTO.setCreatedItemType(CreatedItemType.CUSTOM_OBJECT);

        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        return ResponseEntity.ok(templateService.CustomObjectToDTO(customObject));
    }

    @PutMapping("/updateCustomObject")
    public ResponseEntity<CustomObjectDTO> UpdateCustomObject(@RequestBody CustomObjectDTO customObjectDTO){
        CustomObject customObject = templateService.CustomObjectDTOToEntity(customObjectDTO);

         customObject = templateService.UpdateCustomObject(customObject);
        return ResponseEntity.ok(templateService.CustomObjectToDTO(customObject));
    }

    @DeleteMapping("/deleteCustomObject/{customObjectId}")
    public ResponseEntity<String> DeleteCustomObject(@PathVariable Long customObjectId) {
        templateService.DeleteCustomObject(customObjectId);
        userCreationContentService.DeleteByCreatedItemId(customObjectId,CreatedItemType.CUSTOM_OBJECT);
        return ResponseEntity.ok("Custom Object deleted successfully");
    }

    @GetMapping("/getCustomObjectById/{customObjectId}")
    public ResponseEntity<CustomObjectDTO> GetCustomObjectById(@PathVariable Long customObjectId) {
        CustomObject customObject = templateService.GetCustomObjectById(customObjectId);
        return ResponseEntity.ok(templateService.CustomObjectToDTO(customObject));
    }

    @GetMapping("/GetAllCustomObjectByGameBundleId/{gameBundleId}")
    public ResponseEntity<List<CustomObjectDTO>> GetAllCustomObjectByGameBundleId(@PathVariable Long gameBundleId) {
        List<CustomObject> customObjects = templateService.GetAllCustomObjectByGameBundleId(gameBundleId);
        List<CustomObjectDTO> dts = customObjects.stream().map(templateService::CustomObjectToDTO).toList();
        return ResponseEntity.ok(dts);
    }
    //------------------- CustomObjectAttribute  -------------------------
    @PostMapping("/createManyCustomObjectAttribute")
    public ResponseEntity<List<CustomObjectAttributeDTO>> CreateManyCustomObjectAttribute( @RequestBody List<CustomObjectAttributeDTO> customObjectAttributeDTO) {
        List<CustomObjectAttribute> customObjectAttributes = customObjectAttributeDTO.stream().map(templateService::CustomObjectAttributeDTOtoEntity).toList();
        customObjectAttributes.stream().forEach(c -> c.setId(null));
        customObjectAttributes = templateService.CreateManyCustomObjectAttribute(customObjectAttributes);
        List<CustomObjectAttributeDTO> customObjectAttributeDTOS = customObjectAttributes.stream().map(templateService::CustomObjectAttributeToDTO).toList();
        return ResponseEntity.ok(customObjectAttributeDTOS);
    }


    @PutMapping("/updateManyCustomObjectAttribute")
    public ResponseEntity<List<CustomObjectAttributeDTO>> UpdateManyCustomObjectAttribute(@RequestBody List<CustomObjectAttributeDTO> customObjectAttributeDTO){
        List<CustomObjectAttribute> customObjectAttributes = customObjectAttributeDTO.stream().map(templateService::CustomObjectAttributeDTOtoEntity).toList();

        customObjectAttributes = templateService.UpdateManyCustomObjectAttribute(customObjectAttributes);
        List<CustomObjectAttributeDTO> customObjectAttributeDTOS = customObjectAttributes.stream().map(templateService::CustomObjectAttributeToDTO).toList();
        return ResponseEntity.ok(customObjectAttributeDTOS);
    }

    @DeleteMapping("/deleteManyCustomObjectAttribute")
    public ResponseEntity<String> DeleteManyCustomObjectAttribute(@RequestBody List<Long> customObjectAttributeIds) {
        templateService.DeleteManyCustomObjectAttributeByCustomObjectId(customObjectAttributeIds);
        return ResponseEntity.ok("Custom Object Attributes deleted successfully");
    }


    @GetMapping("/GetAllAttributesByCustomObjectId/{customObjectId}")
    public ResponseEntity<List<CustomObjectAttributeDTO>> GetAllAttributesByCustomObjectId(@PathVariable Long customObjectId) {
        List<CustomObjectAttribute> attributes = templateService.GetAllCustomObjectAttributeByCustomObjectId(customObjectId);
            List<CustomObjectAttributeDTO> dts = attributes.stream().map(templateService::CustomObjectAttributeToDTO).toList();
        return ResponseEntity.ok(dts);
    }
    // ------------------- OptionList    -------------------------
    @PostMapping("/createOptionList/{userId}")
    public ResponseEntity<OptionListDTO>CreateCustomObject(@PathVariable Long userId, @RequestBody OptionListDTO optionListDTO){
        OptionList optionList =templateService.OptionDTOToEntity(optionListDTO);
        optionList.setId(null);
        optionList = templateService.CreateOptionList(optionList);

        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setUserId(userId);
        userCreationContentDTO.setCreatedItemId(optionList.getId());
        userCreationContentDTO.setCreatedItemType(CreatedItemType.OPTION_LIST);

        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        return ResponseEntity.ok(templateService.OptionListToDTO(optionList));
    }
    @PostMapping("/createManyOption/{userId}")
    public ResponseEntity<List<OptionListDTO>> CreateManyOption(@PathVariable Long userId, @RequestBody List<OptionListDTO> optionListDTO){
        List<OptionList> optionLists = optionListDTO.stream().map(templateService::OptionDTOToEntity).toList();
        optionLists.forEach(o ->o.setId(null));
         optionLists = templateService.CreateManyOptionList(optionLists);

        List<OptionListDTO> optionListDTOS = new ArrayList<>();
        //TODO: Refactor this to be more efficient, maybe add a method in service to do this in one step instead of looping here
        for (OptionList optionList : optionLists) {
            UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
            userCreationContentDTO.setUserId(userId);
            userCreationContentDTO.setCreatedItemId(optionList.getId());
            userCreationContentDTO.setCreatedItemType(CreatedItemType.OPTION_LIST);

            userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

            optionListDTOS.add(templateService.OptionListToDTO(optionList));
        }

        return ResponseEntity.ok(optionListDTOS);
    }

    @PutMapping("/updateOptionList")
    public ResponseEntity<OptionListDTO> UpdateCustomObject(@RequestBody OptionListDTO optionListDTO){
        OptionList optionList =templateService.OptionDTOToEntity(optionListDTO);
        optionList = templateService.UpdateOptionList(optionList);
        return ResponseEntity.ok(templateService.OptionListToDTO(optionList));
    }
    @PutMapping("/updateManyOptionList")
    public ResponseEntity<List<OptionListDTO>> UpdateManyOptionList(@RequestBody List<OptionListDTO> optionListDTO){
        List<OptionList> optionLists = optionListDTO.stream().map(templateService::OptionDTOToEntity).toList();
        optionLists.forEach(o ->o.setId(null));
        optionLists = templateService.UpdateManyOptionList(optionLists);
        List<OptionListDTO> optionListDTOS = optionLists.stream().map(templateService::OptionListToDTO).toList();
        return ResponseEntity.ok(optionListDTOS);
    }

    @DeleteMapping("/deleteOptionList/{optionListId}")
    public ResponseEntity<String> DeleteOptionList(@PathVariable Long optionListId) {
        templateService.DeleteOptionList(optionListId);
        userCreationContentService.DeleteByCreatedItemId(optionListId,CreatedItemType.OPTION_LIST);
        return ResponseEntity.ok("Option List deleted successfully");
    }

    @DeleteMapping("/deleteManyOptionList")
    public ResponseEntity<String> DeleteManyOptionList(@RequestBody List<Long> optionListIds) {
        templateService.DeleteManyOptionList(optionListIds);
        for (Long optionListId : optionListIds) {
            userCreationContentService.DeleteByCreatedItemId(optionListId,CreatedItemType.OPTION_LIST);
        }
        return ResponseEntity.ok("Option Lists deleted successfully");
    }

    @GetMapping("/getOptionListById/{optionListId}")
    public ResponseEntity<OptionListDTO> GetOptionListById(@PathVariable Long optionListId) {
        OptionList customObject = templateService.GetOptionListById(optionListId);
        return ResponseEntity.ok(templateService.OptionListToDTO(customObject));
    }

    @GetMapping("/GetAllOptionListByGameBundleId/{gameBundleId}")
    public ResponseEntity<List<OptionListDTO>> GetAllOptionListByGameBundleId(@PathVariable Long gameBundleId) {
        List<OptionList> optionLists = templateService.GetAllOptionListByGameBundleId(gameBundleId);
        List<OptionListDTO> dts = optionLists.stream().map(templateService::OptionListToDTO).toList();
        return ResponseEntity.ok(dts);
    }


    // ------------------- Template      -------------------------

    @PostMapping("/createTemplate/{userId}")
    public ResponseEntity<TemplateDTO>CreateTemplate(@PathVariable Long userId, @RequestBody TemplateDTO templateDTO){
        Template template = templateService.TemplateDTOToEntity(templateDTO);
        template.setId(null);
          template = templateService.CreateTemplate(template);

        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setUserId(userId);
        userCreationContentDTO.setCreatedItemId(template.getId());
        userCreationContentDTO.setCreatedItemType(CreatedItemType.TEMPLATE);

        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        return ResponseEntity.ok(templateService.TemplateToDTO(template));
    }
    @PostMapping("/createManyTemplate/{userId}")
    public ResponseEntity<List<TemplateDTO>> CreateManyTemplate(@PathVariable Long userId, @RequestBody List<TemplateDTO> templateDTO) {
        List<Template> templates = templateService.CreateManyTemplate(templateDTO.stream().map(templateService::TemplateDTOToEntity).toList());

        for(Template template : templates) {
            UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
            userCreationContentDTO.setUserId(userId);
            userCreationContentDTO.setCreatedItemId(template.getId());
            userCreationContentDTO.setCreatedItemType(CreatedItemType.TEMPLATE);

            userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));
        }

        List<TemplateDTO> templateDTOS = templates.stream().map(templateService::TemplateToDTO).toList();
        return ResponseEntity.ok(templateDTOS);

    }

    @PutMapping("/updateTemplate")
    public ResponseEntity<TemplateDTO> UpdateTemplate(@RequestBody TemplateDTO templateDTO){
        Template template = templateService.UpdateTemplate(templateService.TemplateDTOToEntity(templateDTO));
        return ResponseEntity.ok(templateService.TemplateToDTO(template));
    }

    @DeleteMapping("/deleteTemplate/{templateId}")
    public ResponseEntity<String> DeleteTemplateId(@PathVariable Long templateId) {
        templateService.DeleteTemplate(templateId);
        userCreationContentService.DeleteByCreatedItemId(templateId,CreatedItemType.TEMPLATE);
        return ResponseEntity.ok("Template deleted successfully");
    }

    @GetMapping("/getTemplateById/{templateId}")
    public ResponseEntity<TemplateDTO> GetTemplateById(@PathVariable Long templateId) {
        Template template = templateService.GetTemplateById(templateId);
        return ResponseEntity.ok(templateService.TemplateToDTO(template));
    }
    @GetMapping("/GetAllTemplateByGameBundleId/{gameBundleId}")
    public ResponseEntity<List<TemplateDTO>> GetAllTemplateByGameBundleId(@PathVariable Long gameBundleId) {
        List<Template> templates = templateService.GetAllTemplateByGameBundleId(gameBundleId);
        List<TemplateDTO> dts = templates.stream().map(templateService::TemplateToDTO).toList();
        return ResponseEntity.ok(dts);
    }



    // ------------------- TemplateField -------------------------

    @PostMapping("/createTemplateField/{userId}")
    public ResponseEntity<TemplateFieldDTO>CreateTemplateField(@PathVariable Long userId, @RequestBody TemplateFieldDTO templateFieldDTO){
        TemplateField templateField = templateService.CreateTemplateField(templateService.TemplateFieldDTOToEntity(templateFieldDTO));

        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setUserId(userId);
        userCreationContentDTO.setCreatedItemId(templateField.getId());
        userCreationContentDTO.setCreatedItemType(CreatedItemType.TEMPLATE_FIELD);

        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        return ResponseEntity.ok(templateService.TemplateFieldToDTO(templateField));
    }

    @PostMapping("/createManyTemplateField/{userId}")
    public ResponseEntity<List<TemplateFieldDTO>> CreateManyTemplateField(@PathVariable Long userId, @RequestBody List<TemplateFieldDTO> templateFieldDTO) {
        List<TemplateField> templateFields = templateService.CreateManyTemplateField(templateFieldDTO.stream().map(templateService::TemplateFieldDTOToEntity).toList());

        for(TemplateField templateField : templateFields) {
            UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
            userCreationContentDTO.setUserId(userId);
            userCreationContentDTO.setCreatedItemId(templateField.getId());
            userCreationContentDTO.setCreatedItemType(CreatedItemType.TEMPLATE_FIELD);

            userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));
        }

        List<TemplateFieldDTO> templateFieldDTOS = templateFields.stream().map(templateService::TemplateFieldToDTO).toList();
        return ResponseEntity.ok(templateFieldDTOS);
    }

    @PutMapping("/updateTemplateField")
    public ResponseEntity<TemplateFieldDTO> UpdateTemplateField(@RequestBody TemplateFieldDTO templateFieldDTO){
        TemplateField templateField  = templateService.UpdateTemplateField(templateService.TemplateFieldDTOToEntity(templateFieldDTO));
        return ResponseEntity.ok(templateService.TemplateFieldToDTO(templateField));
    }
    @PutMapping("/updateManyTemplateField")
    public ResponseEntity<List<TemplateFieldDTO>> UpdateManyTemplateField(@RequestBody List<TemplateFieldDTO> templateFieldDTO){
        List<TemplateField> templateFields  = templateService.UpdateManyTemplateField(templateFieldDTO.stream().map(templateService::TemplateFieldDTOToEntity).toList());
        List<TemplateFieldDTO> templateFieldDTOS = templateFields.stream().map(templateService::TemplateFieldToDTO).toList();
        return ResponseEntity.ok(templateFieldDTOS);
    }

    @DeleteMapping("/deleteTemplateField/{templateFieldId}")
    public ResponseEntity<String> DeleteTemplateField(@PathVariable Long templateFieldId) {
        templateService.DeleteTemplateField(templateFieldId);
        userCreationContentService.DeleteByCreatedItemId(templateFieldId,CreatedItemType.TEMPLATE_FIELD);
        return ResponseEntity.ok("Template Field deleted successfully");
    }

    @GetMapping("/getTemplateFieldByTemplateId/{templateFieldId}")
    public ResponseEntity<List<TemplateFieldResponseDTO>> GetTemplateFieldByTemplateId(@PathVariable Long templateFieldId) {
        List<TemplateField> templateFields = templateService.GetAllTemplateFieldByTemplateId(templateFieldId);
        System.out.println("TemplateFields: " + templateFields.size());
        List<TemplateFieldResponseDTO> templateFieldResponseDTO = new ArrayList<>();

        for ( TemplateField templateField : templateFields) {

            TemplateFieldResponseDTO fieldMap = new TemplateFieldResponseDTO(
                    templateService.TemplateFieldToDTO(templateField),
                    templateService.OptionListToDTO(templateField.getOptionList())
            );

            templateFieldResponseDTO.add(fieldMap);
        }

        System.out.println("TemplateFieldResponseDTO: " + templateFieldResponseDTO.size());


        return ResponseEntity.ok(templateFieldResponseDTO);
    }






}
