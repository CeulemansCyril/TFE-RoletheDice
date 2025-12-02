package com.example.APIRollTheDice.Model.DTO.GameDTO.Storage;

public class StorageItemDTO {
    private Long id;
    private Long idCustomObject;
    private int quantity;
    private Long idStorage;

    public StorageItemDTO() {
    }

    public StorageItemDTO(Long id, Long idCustomObject, int quantity, Long idStorage) {
        this.id = id;
        this.idCustomObject = idCustomObject;
        this.quantity = quantity;
        this.idStorage = idStorage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCustomObject() {
        return idCustomObject;
    }

    public void setIdCustomObject(Long idCustomObject) {
        this.idCustomObject = idCustomObject;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getIdStorage() {
        return idStorage;
    }

    public void setIdStorage(Long idStorage) {
        this.idStorage = idStorage;
    }
}
