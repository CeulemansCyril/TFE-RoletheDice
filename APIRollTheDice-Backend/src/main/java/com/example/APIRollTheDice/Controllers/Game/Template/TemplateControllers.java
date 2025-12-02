package com.example.APIRollTheDice.Controllers.Game.Template;

import com.example.APIRollTheDice.Enum.CreatedItemType;

import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.CustomObjectDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.OptionListDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.TemplateDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.TemplateFieldDTO;
import com.example.APIRollTheDice.Model.DTO.ReponseDTO.TemplateFieldResponseDTO;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Template.CustomObject;
import com.example.APIRollTheDice.Model.Obj.Game.Template.OptionList;
import com.example.APIRollTheDice.Model.Obj.Game.Template.Template;
import com.example.APIRollTheDice.Model.Obj.Game.Template.TemplateField;
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
        CustomObject customObject = templateService.CreateCustomObject(templateService.CustomObjectDTOToEntity(customObjectDTO));

        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setUserId(userId);
        userCreationContentDTO.setCreatedItemId(customObject.getId());
        userCreationContentDTO.setCreatedItemType(CreatedItemType.CUSTOM_OBJECT);

        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        return ResponseEntity.ok(templateService.CustomObjectToDTO(customObject));
    }

    @PutMapping("/updateCustomObject")
    public ResponseEntity<CustomObjectDTO> UpdateCustomObject(@RequestBody CustomObjectDTO customObjectDTO){
        CustomObject customObject = templateService.UpdateCustomObject(templateService.CustomObjectDTOToEntity(customObjectDTO));
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
    // ------------------- OptionList    -------------------------
    @PostMapping("/createOptionList/{userId}")
    public ResponseEntity<OptionListDTO>CreateCustomObject(@PathVariable Long userId, @RequestBody OptionListDTO optionListDTO){
        OptionList optionList = templateService.CreateOptionList(templateService.OptionDTOToEntity(optionListDTO));

        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setUserId(userId);
        userCreationContentDTO.setCreatedItemId(optionList.getId());
        userCreationContentDTO.setCreatedItemType(CreatedItemType.OPTION_LIST);

        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        return ResponseEntity.ok(templateService.OptionListToDTO(optionList));
    }

    @PutMapping("/updateOptionList")
    public ResponseEntity<OptionListDTO> UpdateCustomObject(@RequestBody OptionListDTO optionListDTO){
        OptionList optionList = templateService.UpdateOptionList(templateService.OptionDTOToEntity(optionListDTO));
        return ResponseEntity.ok(templateService.OptionListToDTO(optionList));
    }

    @DeleteMapping("/deleteOptionList/{optionListId}")
    public ResponseEntity<String> DeleteOptionList(@PathVariable Long optionListId) {
        templateService.DeleteOptionList(optionListId);
        userCreationContentService.DeleteByCreatedItemId(optionListId,CreatedItemType.OPTION_LIST);
        return ResponseEntity.ok("Option List deleted successfully");
    }

    @GetMapping("/getOptionListById/{optionListId}")
    public ResponseEntity<OptionListDTO> GetOptionListById(@PathVariable Long optionListId) {
        OptionList customObject = templateService.GetOptionListById(optionListId);
        return ResponseEntity.ok(templateService.OptionListToDTO(customObject));
    }


    // ------------------- Template      -------------------------

    @PostMapping("/createTemplate/{userId}")
    public ResponseEntity<TemplateDTO>CreateTemplate(@PathVariable Long userId, @RequestBody TemplateDTO templateDTO){
        Template template = templateService.CreateTemplate(templateService.TemplateDTOToEntity(templateDTO));

        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setUserId(userId);
        userCreationContentDTO.setCreatedItemId(template.getId());
        userCreationContentDTO.setCreatedItemType(CreatedItemType.TEMPLATE);

        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        return ResponseEntity.ok(templateService.TemplateToDTO(template));
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

    @PutMapping("/updateTemplateField")
    public ResponseEntity<TemplateFieldDTO> UpdateTemplateField(@RequestBody TemplateFieldDTO templateFieldDTO){
        TemplateField templateField  = templateService.UpdateTemplateField(templateService.TemplateFieldDTOToEntity(templateFieldDTO));
        return ResponseEntity.ok(templateService.TemplateFieldToDTO(templateField));
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

        List<TemplateFieldResponseDTO> templateFieldResponseDTO = new ArrayList<>();

        for ( TemplateField templateField : templateFields) {

            TemplateFieldResponseDTO fieldMap = new TemplateFieldResponseDTO(
                    templateService.TemplateFieldToDTO(templateField),
                    templateService.OptionListToDTO(templateField.getOptionList())
            );

            templateFieldResponseDTO.add(fieldMap);
        }


        return ResponseEntity.ok(templateFieldResponseDTO);
    }





}
