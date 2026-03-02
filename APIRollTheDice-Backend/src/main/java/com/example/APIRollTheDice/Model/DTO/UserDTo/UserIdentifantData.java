package com.example.APIRollTheDice.Model.DTO.UserDTo;

public class UserIdentifantData {
    private Long idUser;
    private String username;

    public UserIdentifantData() {
    }

    public UserIdentifantData(Long idUser, String username) {
        this.idUser = idUser;
        this.username = username;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
