package com.example.APIRollTheDice.Model.DTO.GameDTO.TokenDTO;

import com.example.APIRollTheDice.Enum.TokenType;

public class TokenDTO {
    private long id;
    private String name;
    private String imageURL;
    private TokenType type;
    private boolean canMove;
    private Long idOwner;
    private Long idFiche;
    private Long idGameBundle;

    public TokenDTO(long id, String name, String imageURL, TokenType type, Long idOwner, boolean canMove, Long idFiche, Long idGameBundle) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.type = type;
        this.idOwner = idOwner;
        this.canMove = canMove;
        this.idFiche = idFiche;
        this.idGameBundle = idGameBundle;
    }

    public TokenDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public Long getIdFiche() {
        return idFiche;
    }

    public void setIdFiche(Long idFiche) {
        this.idFiche = idFiche;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public Long getIdGameBundle() {
        return idGameBundle;
    }

    public void setIdGameBundle(Long idGameBundle) {
        this.idGameBundle = idGameBundle;
    }
}
