package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "option_lists")
public class OptionList {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private String name;



    @ElementCollection
    private List<String> options;

    @ManyToOne
    @JoinColumn(name = "game_bundles_id", nullable = false)
    private GameBundle gameBundle;

    public OptionList() {
    }
    public OptionList(long id, String name, List<String> options, GameBundle gameBundle) {
        this.id = id;
        this.name = name;
        this.options = options;
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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public GameBundle getGameBundle() {
        return gameBundle;
    }

    public void setGameBundle(GameBundle gameBundle) {
        this.gameBundle = gameBundle;
    }
}
