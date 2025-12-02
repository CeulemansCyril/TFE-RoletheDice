package com.example.APIRollTheDice.Controllers.Game.LootTable;

import com.example.APIRollTheDice.Enum.CreatedItemType;
import com.example.APIRollTheDice.Model.DTO.GameDTO.LootTableDTO.LootTableDTO;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.Obj.Game.LootTable.LootTable;
import com.example.APIRollTheDice.Service.Game.LootTable.LootTableService;
import com.example.APIRollTheDice.Service.User.UserCreationContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lootTable")
public class LootTableControllers {
    private final LootTableService lootTableService;
    private final UserCreationContentService userCreationContentService;

    public LootTableControllers(LootTableService lootTableService, UserCreationContentService userCreationContentService) {
        this.lootTableService = lootTableService;
        this.userCreationContentService = userCreationContentService;
    }


    @PostMapping("/CreateLootTables/{userId}")
    public ResponseEntity<LootTableDTO> CreateLootTables(@PathVariable Long userId,@RequestBody LootTableDTO lootTableDTO){
        LootTable lootTable = lootTableService.CreateLootTable(lootTableService.LootTableDTOToEntity(lootTableDTO));

        UserCreationContentDTO userCreationContent = new UserCreationContentDTO();

        userCreationContent.setUserId(userId);
        userCreationContent.setCreatedItemId(lootTable.getId());
        userCreationContent.setCreatedItemType(CreatedItemType.LOOT_TABLE);

        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContent));

        return ResponseEntity.ok(lootTableService.LootTableToDTO(lootTable));
    }

    @PutMapping("/UpdateLootTables")
    public ResponseEntity<LootTableDTO> UpdateLootTables(@RequestBody LootTableDTO lootTableDTO){
        LootTable lootTable = lootTableService.UpdateLooTable(lootTableService.LootTableDTOToEntity(lootTableDTO));
        return ResponseEntity.ok(lootTableService.LootTableToDTO(lootTable));
    }

    @DeleteMapping("/DeleteLootTables/{id}")
    public ResponseEntity<String> DeleteLootTables(@PathVariable Long id) {
        lootTableService.DeleteLootTable(id);
        userCreationContentService.DeleteByCreatedItemId(id,CreatedItemType.LOOT_TABLE);

        return ResponseEntity.ok("Loot Table deleted successfully.");
    }

    @GetMapping("/GetLootTableById/{id}")
    public ResponseEntity<LootTableDTO> GetLootTableById(@PathVariable Long id) {
        LootTable lootTable = lootTableService.GetById(id);
        if (lootTable != null) {
            return ResponseEntity.ok(lootTableService.LootTableToDTO(lootTable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/GetLootTableByByGameBundleId/{id}")
    public ResponseEntity<List<LootTableDTO>> GetLootTableByByGameBundleId(@PathVariable Long id) {
        List<LootTable> lootTable = lootTableService.GetAllLootTablesByGameBundleId(id);
        if (lootTable != null) {

            List<LootTableDTO> lootTableDTOs = lootTable.stream()
                    .map(lootTableService::LootTableToDTO)
                    .toList();
            return ResponseEntity.ok(lootTableDTOs);

        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
