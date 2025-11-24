package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.TemplateDTO;

import java.util.List;

public class TemplateFieldDTO {
    private long id;

    private String label;
    private String type;
    private boolean required;

    private Double minValue;
    private Double maxValue;

    private double positionX;
    private double positionY;

    private Long idOptionList;
    private List<Long> idTemplates;

    public TemplateFieldDTO() {
    }

    public TemplateFieldDTO(long id, String label, boolean required, String type, Double maxValue, double positionX, Double minValue, Long idOptionList, double positionY, List<Long> idTemplates) {
        this.id = id;
        this.label = label;
        this.required = required;
        this.type = type;
        this.maxValue = maxValue;
        this.positionX = positionX;
        this.minValue = minValue;
        this.idOptionList = idOptionList;
        this.positionY = positionY;
        this.idTemplates = idTemplates;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public Long getIdOptionList() {
        return idOptionList;
    }

    public void setIdOptionList(Long idOptionList) {
        this.idOptionList = idOptionList;
    }

    public List<Long> getIdTemplates() {
        return idTemplates;
    }

    public void setIdTemplates(List<Long> idTemplates) {
        this.idTemplates = idTemplates;
    }
}
