package com.example.APIRollTheDice.Model.Obj.Game.Template;

import com.example.APIRollTheDice.Model.Obj.Game.GameBundle;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "option_lists")
public class OptionList {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String name;



    @ElementCollection
    private List<String> options;

    @ManyToOne
    @JoinColumn(name = "template_field_id", nullable = false)
    private TemplateField templateField;

    @ManyToOne
    @JoinColumn(name = "game_bundle_id", nullable = false)
    private GameBundle gameBundle;



    public OptionList() {
    }
    public OptionList(Long id, String name, List<String> options, TemplateField templateField, GameBundle gameBundle) {
        this.id = id;
        this.name = name;
        this.options = options;
        this.templateField = templateField;
        this.gameBundle = gameBundle;
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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public TemplateField getTemplateField() {
        return this.templateField;
    }

    public void setTemplateField(TemplateField templateField) {
        this.templateField = templateField;
    }

    public GameBundle getGameBundle() {
        return gameBundle;
    }

    public void setGameBundle(GameBundle gameBundle) {
        this.gameBundle = gameBundle;
    }
}
