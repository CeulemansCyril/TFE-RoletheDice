package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Template;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Money.Value;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Storage.Storage;
import jakarta.persistence.*;

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



    @ElementCollection
    @CollectionTable(name = "custom_object_attributes", joinColumns = @JoinColumn(name = "custom_object_id"))
    @MapKeyColumn(name = "attribute_key")
    @Column(name = "value")
    private Map <String,String> fieldValues;

    @Column(nullable = false)
    private boolean canBeInInventory;

    @Embedded
    private Value price;

    public CustomObject() {
    }

    public CustomObject(Long id, String name, Template template, GameBundle gameBundles, Map<String, String> fieldValues, boolean canBeInInventory, Value price) {
        this.id = id;
        this.name = name;
        this.template = template;
        this.gameBundles = gameBundles;
        this.fieldValues = fieldValues;
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

    public Map<String, String> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Map<String, String> fieldValues) {
        this.fieldValues = fieldValues;
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
