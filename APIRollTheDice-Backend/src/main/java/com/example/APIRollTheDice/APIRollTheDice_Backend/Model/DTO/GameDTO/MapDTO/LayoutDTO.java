package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.MapDTO;


import java.util.List;

public class LayoutDTO {
    private long id;
    private String name;
    private int indexZ;
    private  String backgroundImageURL;
    private Long idMap;
    private List<Long> idTokenPlaced;

    public LayoutDTO(long id, String name, int indexZ, String backgroundImageURL, Long idMap, List<Long> idTokenPlaced) {
        this.id = id;
        this.name = name;
        this.indexZ = indexZ;
        this.backgroundImageURL = backgroundImageURL;
        this.idMap = idMap;
        this.idTokenPlaced = idTokenPlaced;
    }

    public LayoutDTO() {
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

    public int getIndexZ() {
        return indexZ;
    }

    public void setIndexZ(int indexZ) {
        this.indexZ = indexZ;
    }

    public String getBackgroundImageURL() {
        return backgroundImageURL;
    }

    public void setBackgroundImageURL(String backgroundImageURL) {
        this.backgroundImageURL = backgroundImageURL;
    }

    public Long getIdMap() {
        return idMap;
    }

    public void setIdMap(Long idMap) {
        this.idMap = idMap;
    }

    public List<Long> getIdTokenPlaced() {
        return idTokenPlaced;
    }

    public void setIdTokenPlaced(List<Long> idTokenPlaced) {
        this.idTokenPlaced = idTokenPlaced;
    }
}
