package com.example.APIRollTheDice.Controllers.Game.StorageControllers;

import com.example.APIRollTheDice.Model.DTO.GameDTO.Storage.StorageDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.Storage.StorageItemDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Storage.Storage;
import com.example.APIRollTheDice.Model.Obj.Game.Storage.StorageItem;
import com.example.APIRollTheDice.Service.Game.Storage.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storage")
public class StorageControllers {

    private final StorageService storageService;

    public StorageControllers( StorageService storageService) {
        this.storageService = storageService;
    }
    // ------------------- Storage -------------------------
    @PostMapping("/CreateStorage")
    public ResponseEntity<StorageDTO>CreateStorage(StorageDTO storageDTO){
        Storage storage = storageService.CreateStorage(storageService.StorageDTOToEntity(storageDTO));
        return ResponseEntity.ok(storageService.StorageToDTO(storage));
    }

    @PutMapping("/UpdateStorage")
    public ResponseEntity<StorageDTO>UpdateStorage(StorageDTO storageDTO) {
        Storage storage = storageService.UpdateStorage(storageService.StorageDTOToEntity(storageDTO));
        return ResponseEntity.ok(storageService.StorageToDTO(storage));
    }

    @DeleteMapping("/DeleteStorage/{id}")
    public ResponseEntity<String>DeleteStorage(@PathVariable Long id) {
        storageService.DeleteStorage(id);
        return ResponseEntity.ok("Storage deleted successfully.");
    }

    @GetMapping("/GetStorageById/{id}")
    public ResponseEntity<StorageDTO>GetStorageById(@PathVariable Long id) {
        Storage storage = storageService.GetStorageById(id);
        return ResponseEntity.ok(storageService.StorageToDTO(storage));
    }

    @GetMapping("/GetAllStorageByIdGame/{idGame}")
    public ResponseEntity<List<StorageDTO>>GetAllStorageByIdGame(@PathVariable Long idGame) {
        List<Storage> storage = storageService.GetAllStorageByIdGame(idGame);
        List<StorageDTO> storageDTOs = storage.stream()
                .map(storageService::StorageToDTO)
                .toList();
        return ResponseEntity.ok().body(storageDTOs);
    }

    // ------------------- StorageItem -------------------------

    @PutMapping("/CreateStorageItem")
    public ResponseEntity<StorageItemDTO>CreateStorageItem(@RequestBody StorageItemDTO storageDTO) {
        StorageItem storage = storageService.CreateStorageItem(storageService.StorageItemDTOToEntity(storageDTO));
        return ResponseEntity.ok(storageService.StorageItemToDTO(storage));
    }

    @PostMapping("/UpdateStorageItem")
    public ResponseEntity<StorageItemDTO>UpdateStorageItem(@RequestBody StorageItemDTO storageDTO) {
        StorageItem storage = storageService.UpdateStorageItem(storageService.StorageItemDTOToEntity(storageDTO));
        return ResponseEntity.ok(storageService.StorageItemToDTO(storage));
    }

    @DeleteMapping("/DeleteStorageItem/{id}")
    public ResponseEntity<String>DeleteStorageItem(@PathVariable Long id) {
        storageService.DeleteStorageItem(id);
        return ResponseEntity.ok("Storage Item deleted successfully.");
    }

    @GetMapping("/GetStorageItemById/{id}")
    public ResponseEntity<StorageItemDTO>GetStorageItemById(@PathVariable Long id) {
        StorageItem storage = storageService.GetStorageItemById(id);
        return ResponseEntity.ok(storageService.StorageItemToDTO(storage));
    }

    @GetMapping("/GetAllStorageItemsByIdStorage/{idStorage}")
    public ResponseEntity<List<StorageItemDTO>>GetAllStorageItemsByIdStorage(@PathVariable Long idStorage) {
        List<StorageItem> storage = storageService.GetAllStorageItemByStorageId(idStorage);
        List<StorageItemDTO> storageDTOs = storage.stream()
                .map(storageService::StorageItemToDTO)
                .toList();
        return ResponseEntity.ok().body(storageDTOs);
    }


}
