package com.example.APIRollTheDice.Model.Obj.Game.Template;

import com.example.APIRollTheDice.Model.Obj.Game.GameBundle;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "templates")
public class Template {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "game_bundles_id", nullable = false)
    private GameBundle gameBundle;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateField> templateFieldList;

    public Template() {
    }

    public Template(Long id, String name, List<TemplateField> templateFieldList, GameBundle gameBundle) {
        this.id = id;
        this.name = name;
        this.templateFieldList = templateFieldList;
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
