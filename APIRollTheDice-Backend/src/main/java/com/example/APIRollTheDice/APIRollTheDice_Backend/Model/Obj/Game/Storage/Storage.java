package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Storage;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StorageItem> items;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public Storage() {
    }

    public Storage(Long id, String name, String description, List<StorageItem> items, Game game) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.items = items;
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StorageItem> getItems() {
        return items;
    }

    public void setItems(List<StorageItem> items) {
        this.items = items;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
