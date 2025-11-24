package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.MapDTO;

import java.util.List;

public class MapDTO {
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

    private List<Long> idGameBundle;
    private List<Long> idLayouts;

    public MapDTO(Long id, String name, String description, int width, int height, boolean gridEnabled, double cellSize, String gridColor, double gridThickness, List<Long> idGameBundle, String gridType, List<Long> idLayouts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.width = width;
        this.height = height;
        this.gridEnabled = gridEnabled;
        this.cellSize = cellSize;
        this.gridColor = gridColor;
        this.gridThickness = gridThickness;
        this.idGameBundle = idGameBundle;
        this.gridType = gridType;
        this.idLayouts = idLayouts;
    }

    public MapDTO() {
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isGridEnabled() {
        return gridEnabled;
    }

    public void setGridEnabled(boolean gridEnabled) {
        this.gridEnabled = gridEnabled;
    }

    public double getCellSize() {
        return cellSize;
    }

    public void setCellSize(double cellSize) {
        this.cellSize = cellSize;
    }

    public double getGridThickness() {
        return gridThickness;
    }

    public void setGridThickness(double gridThickness) {
        this.gridThickness = gridThickness;
    }

    public String getGridColor() {
        return gridColor;
    }

    public void setGridColor(String gridColor) {
        this.gridColor = gridColor;
    }

    public String getGridType() {
        return gridType;
    }

    public void setGridType(String gridType) {
        this.gridType = gridType;
    }

    public List<Long> getIdGameBundle() {
        return idGameBundle;
    }

    public void setIdGameBundle(List<Long> idGameBundle) {
        this.idGameBundle = idGameBundle;
    }

    public List<Long> getIdLayouts() {
        return idLayouts;
    }

    public void setIdLayouts(List<Long> idLayouts) {
        this.idLayouts = idLayouts;
    }
}
