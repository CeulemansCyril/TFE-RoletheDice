package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Map.Map;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;
import jakarta.persistence.*;


import java.util.List;
import java.util.Set;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    private User creator;

    @ManyToMany
    @JoinTable(
        name = "game_players",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "players_id")
    )
    private List<Player> players;

    @ManyToMany(fetch =  FetchType.LAZY)
    @JoinTable(
        name = "game_play_admins",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> playAdmins;

    @OneToMany(mappedBy = "game",fetch =  FetchType.LAZY)
    private List<ChatChanel> chatChanels;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_bundles_id", nullable = false)
    private GameBundle gameBundle;

    @OneToOne(fetch = FetchType.LAZY)
    private Map activeMap;

    @OneToMany( mappedBy = "game", fetch = FetchType.LAZY)
    private List<Book> books;

    public Game() {
    }

    public Game(Long id, String name, User creator, List<Player> players, List<User> playAdmins, List<ChatChanel> chatChanels,
                GameBundle gameBundle, Map activeMap, List<Book> books
    ) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.players = players;
        this.playAdmins = playAdmins;
        this.chatChanels = chatChanels;
        this.gameBundle = gameBundle;
        this.activeMap = activeMap;
        this.books = books;
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<User> getPlayAdmins() {
        return playAdmins;
    }

    public void setPlayAdmins(List<User> playAdmins) {
        this.playAdmins = playAdmins;
    }

    public List<ChatChanel> getChatChanels() {
        return chatChanels;
    }

    public void setChatChanels(List<ChatChanel> chatChanels) {
        this.chatChanels = chatChanels;
    }

    public GameBundle getGameBundle() {
        return gameBundle;
    }

    public void setGameBundle(GameBundle gameBundle) {
        this.gameBundle = gameBundle;
    }

    public Map getActiveMap() {
        return activeMap;
    }

    public void setActiveMap(Map activeMap) {
        this.activeMap = activeMap;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
