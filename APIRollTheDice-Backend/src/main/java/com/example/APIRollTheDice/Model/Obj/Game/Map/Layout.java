package com.example.APIRollTheDice.Model.Obj.Game.Map;

import com.example.APIRollTheDice.Model.Obj.Game.Token.TokenPlaced;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "layouts")
public class Layout {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int indexZ;

    private  String backgroundImageURL;

    @ManyToOne
    @JoinColumn(name = "map_id", nullable = false)
    private Map map;

    @OneToMany(mappedBy = "layout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TokenPlaced> tokens;

    public Layout() {
    }

    public Layout(long id, String name, String backgroundImageURL, int indexZ, List<TokenPlaced> tokens, Map map) {
        this.id = id;
        this.name = name;
        this.backgroundImageURL = backgroundImageURL;
        this.indexZ = indexZ;
        this.tokens = tokens;
        this.map = map;
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

    public String getBackgroundImageURL() {
        return backgroundImageURL;
    }

    public void setBackgroundImageURL(String backgroundImageURL) {
        this.backgroundImageURL = backgroundImageURL;
    }

    public int getIndexZ() {
        return indexZ;
    }

    public void setIndexZ(int indexZ) {
        this.indexZ = indexZ;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<TokenPlaced> getTokens() {
        return tokens;
    }

    public void setTokens(List<TokenPlaced> tokens) {
        this.tokens = tokens;
    }
}
