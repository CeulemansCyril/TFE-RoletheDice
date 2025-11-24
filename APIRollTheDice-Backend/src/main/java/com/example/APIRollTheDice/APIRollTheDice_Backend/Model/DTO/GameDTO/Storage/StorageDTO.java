package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.Storage;

import java.util.List;

public class StorageDTO {
    private Long id;
    private String name;
    private String description;
    private List<Long> idItems;
    private Long idGame;

    public StorageDTO() {
    }

    public StorageDTO(Long id, String name, String description, List<Long> idItems, Long idGame) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idItems = idItems;
        this.idGame = idGame;
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

    public List<Long> getIdItems() {
        return idItems;
    }

    public void setIdItems(List<Long> idItems) {
        this.idItems = idItems;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }
}
