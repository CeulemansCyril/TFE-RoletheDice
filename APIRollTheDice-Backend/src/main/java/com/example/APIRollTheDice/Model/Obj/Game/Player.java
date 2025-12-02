package com.example.APIRollTheDice.Model.Obj.Game;

import com.example.APIRollTheDice.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.Model.Obj.Game.Storage.Storage;
import com.example.APIRollTheDice.Model.Obj.Game.Template.CustomObject;
import com.example.APIRollTheDice.Model.Obj.User.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "lore_book_id")
    private Book loreBook;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Storage> storages;
    @ManyToOne
    @JoinColumn(name = "character_model_id", nullable = false)
    private CustomObject characterModel;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "players")
    private List<Game> game;

    public Player(Long id, String name, List<Storage> storages, Book loreBook, CustomObject characterModel, User user, List<Game> game) {
        this.id = id;
        this.name = name;
        this.storages = storages;
        this.loreBook = loreBook;
        this.characterModel = characterModel;
        this.user = user;
        this.game = game;
    }

    public Player() {
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

    public Book getLoreBook() {
        return loreBook;
    }

    public void setLoreBook(Book loreBook) {
        this.loreBook = loreBook;
    }

    public List<Storage> getStorages() {
        return storages;
    }

    public void setStorages(List<Storage> storages) {
        this.storages = storages;
    }

    public CustomObject getCharacterModel() {
        return characterModel;
    }

    public void setCharacterModel(CustomObject characterModel) {
        this.characterModel = characterModel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Game> getGame() {
        return game;
    }

    public void setGame(List<Game> game) {
        this.game = game;
    }
}
