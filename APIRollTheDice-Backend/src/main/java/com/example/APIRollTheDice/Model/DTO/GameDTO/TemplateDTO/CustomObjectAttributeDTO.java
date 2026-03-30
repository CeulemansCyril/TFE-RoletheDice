package com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomObjectAttributeDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("IdCustomObject")
    private Long idCustomObject;
    @JsonProperty("IdTemplateField")
    private Long idTemplateField;
    @JsonProperty("Value")
    private String value;
    @JsonProperty("Type")
    private String type;

    public CustomObjectAttributeDTO() {
    }

    public CustomObjectAttributeDTO(Long id, Long idCustomObject, Long idTemplateField, String value, String type) {
        this.id = id;
        this.idCustomObject = idCustomObject;
        this.idTemplateField = idTemplateField;
        this.value = value;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCustomObject() {
        return idCustomObject;
    }

    public void setIdCustomObject(Long idCustomObject) {
        this.idCustomObject = idCustomObject;
    }

    public Long getIdTemplateField() {
        return idTemplateField;
    }

    public void setIdTemplateField(Long idTemplateField) {
        this.idTemplateField = idTemplateField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
