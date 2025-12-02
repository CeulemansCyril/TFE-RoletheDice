package com.example.APIRollTheDice.Model.DTO.GameDTO.TokenDTO;

public class TokenPlacedDTO {
    private Long id;
    private double positionX;
    private double positionY;
    private double rotation;
    private double scale=1.0;
    private boolean sawByEveryone;
    private Long idLayout;
    private Long idToken;

    public TokenPlacedDTO(Long id, double positionX, double positionY, double rotation, double scale, boolean sawByEveryone, Long idToken, Long idLayout) {
        this.id = id;
        this.positionX = positionX;
        this.positionY = positionY;
        this.rotation = rotation;
        this.scale = scale;
        this.sawByEveryone = sawByEveryone;
        this.idToken = idToken;
        this.idLayout = idLayout;
    }

    public TokenPlacedDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public boolean isSawByEveryone() {
        return sawByEveryone;
    }

    public void setSawByEveryone(boolean sawByEveryone) {
        this.sawByEveryone = sawByEveryone;
    }

    public Long getIdLayout() {
        return idLayout;
    }

    public void setIdLayout(Long idLayout) {
        this.idLayout = idLayout;
    }

    public Long getIdToken() {
        return idToken;
    }

    public void setIdToken(Long idToken) {
        this.idToken = idToken;
    }
}
