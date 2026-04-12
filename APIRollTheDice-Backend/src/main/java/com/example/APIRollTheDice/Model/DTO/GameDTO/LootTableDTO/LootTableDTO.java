package com.example.APIRollTheDice.Model.DTO.GameDTO.LootTableDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LootTableDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("LootElements")
    private List<LootElementDTO> lootElements;
    @JsonProperty("IdGameBundle")
    private Long idGameBundle;

    public LootTableDTO(Long id, String name, List<LootElementDTO> lootElements, Long idGameBundle) {
        this.id = id;
        this.name = name;
        this.lootElements = lootElements;
        this.idGameBundle=idGameBundle;
    }

    public LootTableDTO() {
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

    public List<LootElementDTO> getLootElements() {
        return lootElements;
    }

    public void setLootElements(List<LootElementDTO> lootElements) {
        this.lootElements = lootElements;
    }

    public Long getIdGameBundle() {
        return idGameBundle;
    }

    public void setIdGameBundle(Long idGameBundle) {
        this.idGameBundle = idGameBundle;
    }
}
