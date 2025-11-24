package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.LootTable;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "loot_table")
public class LootTable {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "loot_elements", joinColumns = @JoinColumn(name = "loot_table_id"))
    private List<LootElement> lootElements;

    @ManyToOne
    @JoinColumn(name = "game_bundle_id", nullable = false)
    private GameBundle gameBundle;


    public LootTable() {
    }

    public LootTable(Long id, String name, List<LootElement> lootElements,GameBundle gameBundle) {
        this.id = id;
        this.name = name;
        this.lootElements = lootElements;
        this.gameBundle=gameBundle;
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

    public List<LootElement> getLootElements() {
        return lootElements;
    }

    public void setLootElements(List<LootElement> lootElements) {
        this.lootElements = lootElements;
    }

    public GameBundle getGameBundle() {
        return gameBundle;
    }

    public void setGameBundle(GameBundle gameBundle) {
        this.gameBundle = gameBundle;
    }
}
