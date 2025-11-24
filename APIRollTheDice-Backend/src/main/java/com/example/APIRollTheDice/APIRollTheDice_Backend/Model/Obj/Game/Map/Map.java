package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Map;


import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "maps")
public class Map {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private int width;
    private int height;

    private boolean gridEnabled=true;
    private double cellSize=1.0;
    private String gridColor="#000000";
    private double gridThickness=0.05;
    private String gridType="square";

    @ManyToOne
    @JoinColumn(name = "game_bundles_id", nullable = false)
    private List<GameBundle> gameBundle;

    @OneToMany(mappedBy = "map", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Layout> layouts = new ArrayList<>();

    public Map() {
    }

    public Map(Long id, String name, String description, int width, int height, List<GameBundle> gameBundle, List<Layout> layouts, boolean gridEnabled, double cellSize, String gridColor, double gridThickness, String gridType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.width = width;
        this.height = height;
        this.gameBundle = gameBundle;
        this.layouts = layouts;

        this.gridEnabled = gridEnabled;
        this.cellSize = cellSize;
        this.gridColor = gridColor;
        this.gridThickness = gridThickness;
        this.gridType = gridType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<GameBundle> getGameBundle() {
        return gameBundle;
    }

    public void setGameBundle(List<GameBundle> gameBundle) {
        this.gameBundle = gameBundle;
    }

    public List<Layout> getLayouts() {
        return layouts;
    }

    public void setLayouts(List<Layout> layouts) {
        this.layouts = layouts;
    }

    public double getCellSize() {
        return cellSize;
    }

    public void setCellSize(double cellSize) {
        this.cellSize = cellSize;
    }

    public boolean isGridEnabled() {
        return gridEnabled;
    }

    public void setGridEnabled(boolean gridEnabled) {
        this.gridEnabled = gridEnabled;
    }

    public String getGridColor() {
        return gridColor;
    }

    public void setGridColor(String gridColor) {
        this.gridColor = gridColor;
    }

    public double getGridThickness() {
        return gridThickness;
    }

    public void setGridThickness(double gridThickness) {
        this.gridThickness = gridThickness;
    }

    public String getGridType() {
        return gridType;
    }

    public void setGridType(String gridType) {
        this.gridType = gridType;
    }
}
