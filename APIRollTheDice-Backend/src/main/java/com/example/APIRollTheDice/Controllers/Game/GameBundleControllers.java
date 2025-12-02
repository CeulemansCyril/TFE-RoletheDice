package com.example.APIRollTheDice.Controllers.Game;

import com.example.APIRollTheDice.Enum.CreatedItemType;
import com.example.APIRollTheDice.Model.DTO.GameDTO.GameBundleDTO;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.Service.Game.GameBundleService;
import com.example.APIRollTheDice.Service.User.UserCreationContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<GameBundleDTO> CreateMap(@PathVariable Long userId, @RequestBody GameBundleDTO gameBundleDTO){
        GameBundle gameBundle = gameBundleService.CreateGameBundle(gameBundleService.GameBundleDTOToEntity(gameBundleDTO));

        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setUserId(userId);
        userCreationContentDTO.setCreatedItemId(gameBundle.getId());
        userCreationContentDTO.setCreatedItemType(CreatedItemType.GAME_BUNDLE);
        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        return ResponseEntity.ok(gameBundleService.GameBundleToDTO(gameBundle));
    }

    @PutMapping("/UpdateGameBundle")
    public ResponseEntity<GameBundleDTO> UpdateMap(@RequestBody GameBundleDTO gameBundleDTO){
        GameBundle gameBundle = gameBundleService.UpdateGameBundle(gameBundleService.GameBundleDTOToEntity(gameBundleDTO));
        return ResponseEntity.ok(gameBundleService.GameBundleToDTO(gameBundle));
    }

    @DeleteMapping("/DeleteGameBundle/{mapID}")
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

}
