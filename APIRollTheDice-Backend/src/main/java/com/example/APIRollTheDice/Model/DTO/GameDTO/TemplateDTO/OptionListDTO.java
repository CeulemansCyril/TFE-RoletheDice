package com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OptionListDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Options")
    private List<String> options;
    @JsonProperty("IdTemplateField")
    private Long idTemplateField;
    @JsonProperty("IdGameBundle")
    private Long idGameBundle;

    public OptionListDTO() {
    }

    public OptionListDTO(long id, String name, List<String> options, Long idTemplateField, Long idGameBundle) {
        this.id = id;
        this.name = name;
        this.options = options;
        this.idTemplateField = idTemplateField;
        this.idGameBundle = idGameBundle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Long getIdTemplateField() {
        return idTemplateField;
    }

    public void setIdTemplateField(Long idTemplateField) {
        this.idTemplateField = idTemplateField;
    }

    public Long getIdGameBundle() {
        return idGameBundle;
    }

    public void setIdGameBundle(Long idGameBundle) {
        this.idGameBundle = idGameBundle;
    }


}
