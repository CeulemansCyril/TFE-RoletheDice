package com.example.APIRollTheDice.Model.Obj.Game.Token;

import com.example.APIRollTheDice.Enum.TokenType;
import com.example.APIRollTheDice.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.Model.Obj.Game.Template.CustomObject;
import com.example.APIRollTheDice.Model.Obj.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private String name;
    private String imageURL;
    private TokenType type;


    private boolean canMove;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "custom_objects_id", nullable = false)
    private CustomObject fiche;

    @ManyToOne
    @JoinColumn(name = "game_bundles_id", nullable = false)
    private GameBundle gameBundle;

    public Token() {
    }

    public Token(long id, String name, String imageURL, TokenType type, boolean canMove, User owner, CustomObject fiche, GameBundle gameBundle) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.type = type;

        this.canMove = canMove;
        this.owner = owner;
        this.fiche = fiche;
        this.gameBundle = gameBundle;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public CustomObject getFiche() {
        return fiche;
    }

    public void setFiche(CustomObject fiche) {
        this.fiche = fiche;
    }

    public GameBundle getGameBundle() {
        return gameBundle;
    }

    public void setGameBundle(GameBundle gameBundle) {
        this.gameBundle = gameBundle;
    }
}
