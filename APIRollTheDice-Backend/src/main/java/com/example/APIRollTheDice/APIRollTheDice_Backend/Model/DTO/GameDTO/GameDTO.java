package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO;

import java.util.List;

public class GameDTO {
    private Long id;
    private String name;
    private Long idCreator;
    private List<Long> idPlayers;
    private List<Long> idPlayAdmins;
    private List<Long> idChatChanels;
    private Long idGameBundle;
    private Long idActiveMap;
    private List<Long> idBooks;

    public GameDTO(Long id, String name, Long idCreator, List<Long> idPlayers, List<Long> idPlayAdmins, List<Long> idChatChanels, Long idGameBundle, Long idActiveMap, List<Long> idBooks) {
        this.id = id;
        this.name = name;
        this.idCreator = idCreator;
        this.idPlayers = idPlayers;
        this.idPlayAdmins = idPlayAdmins;
        this.idChatChanels = idChatChanels;
        this.idGameBundle = idGameBundle;
        this.idActiveMap = idActiveMap;
        this.idBooks = idBooks;
    }

    public GameDTO() {
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

    public List<Long> getIdPlayers() {
        return idPlayers;
    }

    public void setIdPlayers(List<Long> idPlayers) {
        this.idPlayers = idPlayers;
    }

    public List<Long> getIdPlayAdmins() {
        return idPlayAdmins;
    }

    public void setIdPlayAdmins(List<Long> idPlayAdmins) {
        this.idPlayAdmins = idPlayAdmins;
    }

    public Long getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(Long idCreator) {
        this.idCreator = idCreator;
    }

    public List<Long> getIdChatChanels() {
        return idChatChanels;
    }

    public void setIdChatChanels(List<Long> idChatChanels) {
        this.idChatChanels = idChatChanels;
    }

    public Long getIdGameBundle() {
        return idGameBundle;
    }

    public void setIdGameBundle(Long idGameBundle) {
        this.idGameBundle = idGameBundle;
    }

    public Long getIdActiveMap() {
        return idActiveMap;
    }

    public void setIdActiveMap(Long idActiveMap) {
        this.idActiveMap = idActiveMap;
    }

    public List<Long> getIdBooks() {
        return idBooks;
    }

    public void setIdBooks(List<Long> idBooks) {
        this.idBooks = idBooks;
    }
}
