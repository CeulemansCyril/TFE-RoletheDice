package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Enum.BookTypes;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookTypes type;

    @OneToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private List<Pages> pages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_bundles_id")
    private GameBundle gameBundle;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;
    public Book() {
    }

    public Book(Long id, String title, BookTypes type, List<Pages> pages, Game game, GameBundle gameBundle) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.pages = pages;
        this.game = game;
        this.gameBundle = gameBundle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pages> getPages() {
        return pages;
    }

    public void setPages(List<Pages> pages) {
        this.pages = pages;
    }

    public BookTypes getType() {
        return type;
    }

    public void setType(BookTypes type) {
        this.type = type;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameBundle getGameBundle() {
        return gameBundle;
    }

    public void setGameBundle(GameBundle gameBundle) {
        this.gameBundle = gameBundle;
    }
}
