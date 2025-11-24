package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Token;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Map.Layout;
import jakarta.persistence.*;

@Entity
@Table(name = "TokenPlaced")
public class TokenPlaced {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private double positionX;
    private double positionY;
    private double rotation;
    private double scale=1.0;
    private boolean sawByEveryone;

    @ManyToOne
    @JoinColumn(name = "layouts_id", nullable = false)
    private Layout layout;

    @ManyToOne
    @JoinColumn(name = "token_id", nullable = false)
    private Token token;

    public TokenPlaced() {
    }

    public TokenPlaced(Long id, double positionX, double positionY, double rotation, double scale, Layout layout, Token token, boolean sawByEveryone) {
        this.id = id;
        this.positionX = positionX;
        this.positionY = positionY;
        this.rotation = rotation;
        this.scale = scale;
        this.layout = layout;
        this.token = token;
        this.sawByEveryone = sawByEveryone;
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

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public boolean isSawByEveryone() {
        return sawByEveryone;
    }

    public void setSawByEveryone(boolean sawByEveryone) {
        this.sawByEveryone = sawByEveryone;
    }
}
