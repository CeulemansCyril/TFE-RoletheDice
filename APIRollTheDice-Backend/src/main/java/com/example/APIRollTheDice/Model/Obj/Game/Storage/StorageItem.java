package com.example.APIRollTheDice.Model.Obj.Game.Storage;

import com.example.APIRollTheDice.Model.Obj.Game.Template.CustomObject;
import jakarta.persistence.*;

@Entity
@Table(name = "storage_items")
public class StorageItem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_object_id", nullable = false)
    private CustomObject customObject;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;

    public StorageItem() {
    }

    public StorageItem(Long id, CustomObject customObject, int quantity, Storage storage) {
        this.id = id;
        this.customObject = customObject;
        this.quantity = quantity;
        this.storage = storage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomObject getCustomObject() {
        return customObject;
    }

    public void setCustomObject(CustomObject customObject) {
        this.customObject = customObject;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
