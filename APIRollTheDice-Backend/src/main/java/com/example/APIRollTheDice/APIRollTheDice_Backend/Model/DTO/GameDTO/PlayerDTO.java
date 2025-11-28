package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO;

import java.util.List;

public class PlayerDTO {
    private Long id;
    private String name;
    private Long idLoreBook;
    private List<Long> idStorages;
    private Long idCharacterModel;
    private Long idUser;
    private List<Long> idGame;

    public PlayerDTO(Long id, String name, Long idLoreBook, List<Long> idStorages, Long idCharacterModel, Long idUser, List<Long> idGame) {
        this.id = id;
        this.name = name;
        this.idLoreBook = idLoreBook;
        this.idStorages = idStorages;
        this.idCharacterModel = idCharacterModel;
        this.idUser = idUser;
        this.idGame = idGame;
    }

    public PlayerDTO() {
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

    public List<Long> getIdStorages() {
        return idStorages;
    }

    public void setIdStorages(List<Long> idStorages) {
        this.idStorages = idStorages;
    }

    public Long getIdCharacterModel() {
        return idCharacterModel;
    }

    public void setIdCharacterModel(Long idCharacterModel) {
        this.idCharacterModel = idCharacterModel;
    }

    public List<Long> getIdGame() {
        return idGame;
    }

    public void setIdGame(List<Long> idGame) {
        this.idGame = idGame;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdLoreBook() {
        return idLoreBook;
    }

    public void setIdLoreBook(Long idLoreBook) {
        this.idLoreBook = idLoreBook;
    }
}
