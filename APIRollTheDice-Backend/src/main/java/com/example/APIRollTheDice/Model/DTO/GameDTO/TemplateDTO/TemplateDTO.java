package com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TemplateDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("IdGameBundle")
    private Long idGameBundle;
    @JsonProperty("IdTemplateFieldList")
    private List<Long> idTemplateFieldList;

    public TemplateDTO() {
    }

    public TemplateDTO(Long id, String name, Long idGameBundle, List<Long> idTemplateFieldList) {
        this.id = id;
        this.name = name;
        this.idGameBundle = idGameBundle;
        this.idTemplateFieldList = idTemplateFieldList;
    }


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getIdGameBundle() {
        return idGameBundle;
    }

    public void setIdGameBundle(Long idGameBundle) {
        this.idGameBundle = idGameBundle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getIdTemplateFieldList() {
        return idTemplateFieldList;
    }

    public void setIdTemplateFieldList(List<Long> idTemplateFieldList) {
        this.idTemplateFieldList = idTemplateFieldList;
    }
}
