package com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO;

import java.util.List;

public class OptionListDTO {
    private long id;
    private String name;
    private List<String> options;
    private Long idTemplateField;

    public OptionListDTO() {
    }

    public OptionListDTO(long id, String name, List<String> options, Long idTemplateField) {
        this.id = id;
        this.name = name;
        this.options = options;
        this.idTemplateField = idTemplateField;
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
}
