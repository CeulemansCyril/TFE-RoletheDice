package com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO;

import com.example.APIRollTheDice.Model.Obj.Game.Money.Value;

import java.util.Map;

public class CustomObjectDTO {
    private Long id;
    private String name;
    private Long idTemplate;
    private Long idGameBundles;
    private Map<String,String> fieldValues;
    private boolean canBeInInventory;
    private Value price;

    public CustomObjectDTO(Long id, String name, Long idTemplate, Long idGameBundles, boolean canBeInInventory, Value price, Map<String, String> fieldValues) {
        this.id = id;
        this.name = name;
        this.idTemplate = idTemplate;
        this.idGameBundles = idGameBundles;
        this.canBeInInventory = canBeInInventory;
        this.price = price;
        this.fieldValues = fieldValues;
    }

    public CustomObjectDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdTemplate() {
        return idTemplate;
    }

    public void setIdTemplate(Long idTemplate) {
        this.idTemplate = idTemplate;
    }

    public Long getIdGameBundles() {
        return idGameBundles;
    }

    public void setIdGameBundles(Long idGameBundles) {
        this.idGameBundles = idGameBundles;
    }

    public Map<String, String> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Map<String, String> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public boolean isCanBeInInventory() {
        return canBeInInventory;
    }

    public void setCanBeInInventory(boolean canBeInInventory) {
        this.canBeInInventory = canBeInInventory;
    }

    public Value getPrice() {
        return price;
    }

    public void setPrice(Value price) {
        this.price = price;
    }
}
