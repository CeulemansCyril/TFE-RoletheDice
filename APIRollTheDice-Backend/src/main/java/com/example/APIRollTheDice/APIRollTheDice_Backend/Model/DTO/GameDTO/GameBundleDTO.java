package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO;

import java.util.List;

public class GameBundleDTO {
    private Long id;
    private String name;
    private Long idCreator;
    private List<Long> idGame;
    private List<Long> idTokens;
    private List<Long> idBooks;
    private List<Long> idCurrencies;
    private List<Long> idLootTables;
    private List<Long> idMap;

    public GameBundleDTO(Long id, String name, Long idCreator, List<Long> idGame, List<Long> idBooks, List<Long> idTokens, List<Long> idCurrencies, List<Long> idLootTables, List<Long> idMap) {
        this.id = id;
        this.name = name;
        this.idCreator = idCreator;
        this.idGame = idGame;
        this.idBooks = idBooks;
        this.idTokens = idTokens;
        this.idCurrencies = idCurrencies;
        this.idLootTables = idLootTables;
        this.idMap = idMap;
    }

    public GameBundleDTO() {
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

    public Long getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(Long idCreator) {
        this.idCreator = idCreator;
    }

    public List<Long> getIdGame() {
        return idGame;
    }

    public void setIdGame(List<Long> idGame) {
        this.idGame = idGame;
    }

    public List<Long> getIdTokens() {
        return idTokens;
    }

    public void setIdTokens(List<Long> idTokens) {
        this.idTokens = idTokens;
    }

    public List<Long> getIdBooks() {
        return idBooks;
    }

    public void setIdBooks(List<Long> idBooks) {
        this.idBooks = idBooks;
    }

    public List<Long> getIdCurrencies() {
        return idCurrencies;
    }

    public void setIdCurrencies(List<Long> idCurrencies) {
        this.idCurrencies = idCurrencies;
    }

    public List<Long> getIdLootTables() {
        return idLootTables;
    }

    public void setIdLootTables(List<Long> idLootTables) {
        this.idLootTables = idLootTables;
    }

    public List<Long> getIdMap() {
        return idMap;
    }

    public void setIdMap(List<Long> idMap) {
        this.idMap = idMap;
    }
}
