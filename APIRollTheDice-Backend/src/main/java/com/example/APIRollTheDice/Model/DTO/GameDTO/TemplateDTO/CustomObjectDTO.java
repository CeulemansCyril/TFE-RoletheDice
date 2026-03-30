package com.example.APIRollTheDice.Model.DTO.GameDTO.TemplateDTO;

import com.example.APIRollTheDice.Model.DTO.GameDTO.MoneyDTO.ValueDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Value;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class CustomObjectDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("IdTemplate")
    private Long idTemplate;
    @JsonProperty("IdGameBundles")
    private Long idGameBundles;
    @JsonProperty("AttributeDTOs")
    private List<CustomObjectAttributeDTO> attributeDTOs;
    @JsonProperty("CanBeInInventory")
    private boolean canBeInInventory;
    @JsonProperty("Price")
    private ValueDTO price;

    public CustomObjectDTO(Long id, String name, Long idTemplate, Long idGameBundles, boolean canBeInInventory, ValueDTO price, List<CustomObjectAttributeDTO> attributes) {
        this.id = id;
        this.name = name;
        this.idTemplate = idTemplate;
        this.idGameBundles = idGameBundles;
        this.canBeInInventory = canBeInInventory;
        this.price = price;
        this.attributeDTOs = attributes;
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

    public List<CustomObjectAttributeDTO> getAttributes() {
        return attributeDTOs;
    }

    public void setAttributes(List<CustomObjectAttributeDTO> attributes) {
        this.attributeDTOs = attributes;
    }

    public boolean isCanBeInInventory() {
        return canBeInInventory;
    }

    public void setCanBeInInventory(boolean canBeInInventory) {
        this.canBeInInventory = canBeInInventory;
    }

    public ValueDTO getPrice() {
        return price;
    }

    public void setPrice(ValueDTO price) {
        this.price = price;
    }
}
