package com.example.APIRollTheDice.Controllers.Game;

import com.example.APIRollTheDice.Enum.CreatedItemType;
import com.example.APIRollTheDice.Model.DTO.GameDTO.GameBundleDTO;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.Service.Game.GameBundleService;
import com.example.APIRollTheDice.Service.User.UserCreationContentService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gameBundle")
public class GameBundleControllers {
    private UserCreationContentService userCreationContentService;
    private GameBundleService gameBundleService;

    public GameBundleControllers(UserCreationContentService userCreationContentService, GameBundleService gameBundleService) {
        this.userCreationContentService = userCreationContentService;
        this.gameBundleService = gameBundleService;
    }

    @PostMapping("/CreateGameBundle/{userID}")
    public ResponseEntity<GameBundleDTO> CreateGameBundle(@PathVariable Long userID, @RequestBody GameBundleDTO gameBundleDTO){
        GameBundle gameBundle = gameBundleService.GameBundleDTOToEntity(gameBundleDTO);
        gameBundle.setId(null);
        gameBundle= gameBundleService.CreateGameBundle(gameBundle);




        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setUserId(userID);
        userCreationContentDTO.setCreatedItemId(gameBundle.getId());
        userCreationContentDTO.setCreatedItemType(CreatedItemType.GAME_BUNDLE);
        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        GameBundleDTO responseDTO = gameBundleService.GameBundleToDTO(gameBundle);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/UpdateGameBundle")
    public ResponseEntity<GameBundleDTO> UpdateGameBundle(@RequestBody GameBundleDTO gameBundleDTO){
        GameBundle gameBundle = gameBundleService.UpdateGameBundle(gameBundleService.GameBundleDTOToEntity(gameBundleDTO));
        return ResponseEntity.ok(gameBundleService.GameBundleToDTO(gameBundle));
    }

    @Transactional
    @DeleteMapping("/DeleteGameBundle/{gameBundleID}")
    public ResponseEntity<String> DeleteGameBundle(@PathVariable Long gameBundleID) {
        gameBundleService.DeleteGameBundle(gameBundleID);
        userCreationContentService.DeleteByCreatedItemId(gameBundleID,CreatedItemType.GAME_BUNDLE);
        return ResponseEntity.ok("Map deleted successfully.");
    }

    @GetMapping("/GetGameBundleById/{GameBundleId}")
    public ResponseEntity<GameBundleDTO> GetMapAllMapByGameBundleId(@PathVariable Long GameBundleId){
        GameBundle gameBundle = gameBundleService.GetGameBundleById(GameBundleId);
        return ResponseEntity.ok(gameBundleService.GameBundleToDTO(gameBundle));
    }

    @GetMapping("/GetGameBundlesByUserId/{UserId}")
    public ResponseEntity<List<GameBundleDTO>> GetAllGameBundleByUserId(@PathVariable Long UserId){
        List<GameBundle> gameBundles = gameBundleService.GetAllGameBundleByUserId(UserId);
        List<GameBundleDTO> gameBundleDTOs = gameBundles.stream().map(gameBundleService::GameBundleToDTO).toList();
        return ResponseEntity.ok(gameBundleDTOs);
    }

}
