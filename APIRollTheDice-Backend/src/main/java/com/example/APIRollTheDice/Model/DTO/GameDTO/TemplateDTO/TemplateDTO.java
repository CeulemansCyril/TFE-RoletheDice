package com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO;

import java.util.List;

public class TemplateDTO {
    private long id;
    private String name;
    private Long idGameBundle;
    private List<Long> idTemplateFieldList;

    public TemplateDTO() {
    }

    public TemplateDTO(long id, String name, Long idGameBundle, List<Long> idTemplateFieldList) {
        this.id = id;
        this.name = name;
        this.idGameBundle = idGameBundle;
        this.idTemplateFieldList = idTemplateFieldList;
    }


    public long getId() {
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
