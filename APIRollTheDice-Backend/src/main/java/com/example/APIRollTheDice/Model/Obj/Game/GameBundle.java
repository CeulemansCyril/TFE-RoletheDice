package com.example.APIRollTheDice.Model.Obj.Game;


import com.example.APIRollTheDice.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.Model.Obj.Game.LootTable.LootTable;
import com.example.APIRollTheDice.Model.Obj.Game.Map.Map;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Currency;
import com.example.APIRollTheDice.Model.Obj.Game.Token.Token;
import com.example.APIRollTheDice.Model.Obj.User.User;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "game_bundles")
public class GameBundle {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User creator;

    @OneToMany(mappedBy = "gameBundle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> games;

    @OneToMany(mappedBy = "gameBundle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Token> tokens;



    @OneToMany(mappedBy = "gameBundle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    @ManyToMany
    @JoinTable(
            name = "gameBundle_currency",
            joinColumns = @JoinColumn(name = "game_bundles_id"),
            inverseJoinColumns = @JoinColumn(name = "currency_id")
    )
    private List<Currency> currencies;

    @OneToMany(mappedBy = "gameBundle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LootTable> lootTables;

    @ManyToMany
    @JoinTable(
            name = "gameBundle_maps",
            joinColumns = @JoinColumn(name = "gameBundle_id"),
            inverseJoinColumns = @JoinColumn(name = "map_id")
    )
    private List<Map> maps;

    public GameBundle() {
    }

    public GameBundle(Long id, String name, User creator, List<Token> tokens, List<Game> games, List<Map> maps, List<Currency> currencies,
                      List<Book> books, List<LootTable> lootTables
    ) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.tokens = tokens;
        this.games = games;
        this.maps = maps;
        this.currencies = currencies;
        this.books = books;
        this.lootTables = lootTables;
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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Map> getMaps() {
        return maps;
    }

    public void setMaps(List<Map> maps) {
        this.maps = maps;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<LootTable> getLootTables() {
        return lootTables;
    }

    public void setLootTables(List<LootTable> lootTables) {
        this.lootTables = lootTables;
    }
}
