package com.example.APIRollTheDice.Model.Obj.Game.Template;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "template_fields")
public class TemplateField {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private String label;
    private String type;
    private boolean required;

    private Double minValue;
    private Double maxValue;

    private double positionX;
    private double positionY;

    @ManyToOne
    @JoinColumn(name = "option_lists_id")
    private OptionList optionList;

    @ManyToMany(mappedBy = "templateFieldList")
    private List<Template> templates;

    public TemplateField() {
    }

    public TemplateField(long id, String label, String type, boolean required, Double minValue, Double maxValue, double positionX, double positionY, OptionList optionList, List<Template> template) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.required = required;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.positionX = positionX;
        this.positionY = positionY;
        this.optionList = optionList;
        this.templates = template;
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

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public OptionList getOptionList() {
        return optionList;
    }

    public void setOptionList(OptionList optionList) {
        this.optionList = optionList;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplate(List<Template> template) {
        this.templates = template;
    }
}
