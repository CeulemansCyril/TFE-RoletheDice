package com.example.APIRollTheDice.APIRollTheDice_Backend.Service.Game.Storage;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.GameInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.StorageInterface.StorageInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.StorageInterface.StorageItemInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TemplateInterface.CustomObjectInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Storage.StorageItemMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.Storage.StorageMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.Storage.StorageDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.Storage.StorageItemDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Storage.Storage;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Storage.StorageItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageService {
    private final StorageMapper storageMapper;
    private final StorageItemMapper storageItemMapper;
    private final StorageInterface storageInterface;
    private final StorageItemInterface storageItemInterface;
    private final GameInterface gameInterface;
    private final CustomObjectInterface customObjectInterface;

    public StorageService(StorageMapper storageMapper, StorageItemMapper storageItemMapper, StorageInterface storageInterface, StorageItemInterface storageItemInterface, GameInterface gameInterface, CustomObjectInterface customObjectInterface) {
        this.storageMapper = storageMapper;
        this.storageItemMapper = storageItemMapper;
        this.storageInterface = storageInterface;
        this.storageItemInterface = storageItemInterface;
        this.gameInterface = gameInterface;
        this.customObjectInterface = customObjectInterface;
    }

    public Storage CreateStorage(Storage storage){
        return storageInterface.save(storage);
    }

    public Storage UpdateStorage(Storage storage){
        Storage existing = storageInterface.findById(storage.getId()).orElseThrow(()-> new NotFoundException("Storage not found"));

        existing.setItems(storage.getItems());
        existing.setGame(storage.getGame());
        existing.setDescription(storage.getDescription());
        existing.setName(storage.getName());

        return storageInterface.save(existing);
    }

    public void DeleteStorage(Long id){
        if(storageInterface.existsById(id))storageInterface.deleteById(id);
        else throw new NotFoundException("Storage not found");
    }

    public Storage GetStorageById(Long id){
        return storageInterface.findById(id).orElseThrow(() -> new NotFoundException ("Storage not found"));
    }

    public List<Storage> GetAllStorageByIdGame(Long id){
        return storageInterface.findAllByGame_id(id);
    }

    public StorageDTO StorageToDTO(Storage storage){
        return storageMapper.toDTO(storage);
    }

    public Storage StorageDTOToEntity(StorageDTO storageDTO){
        Storage storage = storageMapper.toEntity(storageDTO);

        storage.setGame(gameInterface.findById(storageDTO.getIdGame()).get());
        storage.setItems(storageItemInterface.findAllById(storageDTO.getIdItems()));

        return storage;

    }


    public StorageItem CreateStorageItem(StorageItem storageItem){
        return storageItemInterface.save(storageItem);
    }

    public StorageItem UpdateStorageItem(StorageItem storageItem){
        StorageItem existing = storageItemInterface.findById(storageItem.getId()).orElseThrow(() -> new NotFoundException ("StorageItem not found"));

        existing.setCustomObject(storageItem.getCustomObject());
        existing.setStorage(storageItem.getStorage());
        existing.setQuantity(storageItem.getQuantity());

        return storageItemInterface.save(storageItem);
    }

    public void DeleteStorageItem(Long id){
        if(storageItemInterface.existsById(id))storageItemInterface.deleteById(id);
        else throw  new NotFoundException("StorageItem not found");
    }

    public StorageItem GetStorageItemById(Long id){
        return storageItemInterface.findById(id).orElseThrow(() -> new NotFoundException ("StorageItem not found"));
    }

    public List<StorageItem> GetAllStorageItemByStorageId(Long id){
        return storageItemInterface.findAllByStorage_id(id);
    }

    public StorageItemDTO StorageItemToDTO(StorageItem storageItem){
        return storageItemMapper.toDTO(storageItem);
    }

    public StorageItem StorageItemDTOToEntity(StorageItemDTO storageItemDTO){
        StorageItem storageItem = storageItemMapper.toEntity(storageItemDTO);

        storageItem.setStorage(storageInterface.findById(storageItemDTO.getIdStorage()).orElseThrow(() -> new NotFoundException("Storage not found")));
        storageItem.setCustomObject(customObjectInterface.findById(storageItemDTO.getIdCustomObject()).orElseThrow(() -> new NotFoundException("CustomObject not found")));

        return storageItem;
    }





}
