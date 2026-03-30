package com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TemplateFieldDTO {
    @JsonProperty("Id")
    private long id;

    @JsonProperty("Label")
    private String label;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Required")
    private boolean required;
    @JsonProperty("MinValue")
    private Double minValue;
    @JsonProperty("MaxValue")
    private Double maxValue;
    @JsonProperty("PositionX")
    private double positionX;
    @JsonProperty("PositionY")
    private double positionY;
    @JsonProperty("Width")
    private double width;
    @JsonProperty("Height")
    private double height;
    @JsonProperty("IdOptionList")
    private Long idOptionList;
    @JsonProperty("IdTemplates")
    private Long idTemplates;

    public TemplateFieldDTO() {
    }

    public TemplateFieldDTO(long id, String label, boolean required, String type, Double maxValue, double positionX, Double minValue, Long idOptionList, double positionY, Long idTemplates, double width, double height) {
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
        this.width = width;
        this.height = height;
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

    public Long getIdTemplates() {
        return idTemplates;
    }

    public void setIdTemplates(Long idTemplates) {
        this.idTemplates = idTemplates;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
