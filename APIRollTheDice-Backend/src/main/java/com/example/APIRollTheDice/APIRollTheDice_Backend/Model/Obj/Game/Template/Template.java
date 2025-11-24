package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "templates")
public class Template {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "game_bundles_id", nullable = false)
    private GameBundle gameBundle;

    @ManyToMany
    @JoinTable(
            name = "template_template_fields",
            joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "template_fields_id")
    )
    private List<TemplateField> templateFieldList;

    public Template() {
    }

    public Template(long id, String name, List<TemplateField> templateFieldList, GameBundle gameBundle) {
        this.id = id;
        this.name = name;
        this.templateFieldList = templateFieldList;
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

    public GameBundle getGameBundle() {
        return gameBundle;
    }

    public void setGameBundle(GameBundle gameBundle) {
        this.gameBundle = gameBundle;
    }

    public List<TemplateField> getTemplateFieldList() {
        return templateFieldList;
    }

    public void setTemplateFieldList(List<TemplateField> templateFieldList) {
        this.templateFieldList = templateFieldList;
    }
}
