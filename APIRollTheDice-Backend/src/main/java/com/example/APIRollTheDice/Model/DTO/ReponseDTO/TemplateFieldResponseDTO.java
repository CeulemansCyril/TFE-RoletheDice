package com.example.APIRollTheDice.Model.DTO.ReponseDTO;


import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.OptionListDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO.TemplateFieldDTO;



public class TemplateFieldResponseDTO {
    private TemplateFieldDTO templateField;
    private OptionListDTO optionList;

    public TemplateFieldResponseDTO(TemplateFieldDTO templateField, OptionListDTO optionList) {
        this.templateField = templateField;
        this.optionList = optionList;
    }

    public TemplateFieldResponseDTO() {
    }

    public TemplateFieldDTO getTemplateField() {
        return templateField;
    }

    public void setTemplateField(TemplateFieldDTO templateField) {
        this.templateField = templateField;
    }

    public OptionListDTO getOptionList() {
        return optionList;
    }

    public void setOptionList(OptionListDTO optionList) {
        this.optionList = optionList;
    }
}
