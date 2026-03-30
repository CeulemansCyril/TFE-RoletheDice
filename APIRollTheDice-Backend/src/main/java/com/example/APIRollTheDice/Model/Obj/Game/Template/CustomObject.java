package com.example.APIRollTheDice.Model.Obj.Game.Template;

import com.example.APIRollTheDice.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Value;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "custom_objects")
public class CustomObject {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @ManyToOne
    @JoinColumn(name = "game_bundles_id", nullable = false)
    private GameBundle gameBundles;



    @OneToMany(mappedBy = "customObject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomObjectAttribute> attributes;

    @Column(nullable = false)
    private boolean canBeInInventory;

    @Embedded
    private Value price;

    public CustomObject() {
    }

    public CustomObject(Long id, String name, Template template, GameBundle gameBundles, List<CustomObjectAttribute> attributes, boolean canBeInInventory, Value price) {
        this.id = id;
        this.name = name;
        this.template = template;
        this.gameBundles = gameBundles;
        this.attributes = attributes;
        this.canBeInInventory = canBeInInventory;
        this.price = price;
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

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public GameBundle getGameBundles() {
        return gameBundles;
    }

    public void setGameBundles(GameBundle gameBundles) {
        this.gameBundles = gameBundles;
    }

    public List<CustomObjectAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CustomObjectAttribute> attributes) {
        this.attributes = attributes;
    }

    public boolean isCanBeInInventory() {
        return canBeInInventory;
    }

    public void setCanBeInInventory(boolean canBeInInventory) {
        this.canBeInInventory = canBeInInventory;
    }

    public Value getPrice() {
        return price;
    }

    public void setPrice(Value price) {
        this.price = price;
    }
}
