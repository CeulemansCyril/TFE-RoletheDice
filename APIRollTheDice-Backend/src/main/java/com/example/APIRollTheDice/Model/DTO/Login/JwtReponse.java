package com.example.APIRollTheDice.Model.DTO.Login;

import com.example.APIRollTheDice.Enum.RoleUser;

public class JwtReponse {
    private String token;
    private String type = "Bearer";
    private RoleUser role;
    private Long idUser;

    public JwtReponse() {
    }

    public JwtReponse(String token, RoleUser role, Long idUser) {
        this.idUser = idUser;
        this.role = role;
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
