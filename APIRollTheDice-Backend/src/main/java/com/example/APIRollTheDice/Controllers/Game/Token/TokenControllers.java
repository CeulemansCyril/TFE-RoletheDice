package com.example.APIRollTheDice.Controllers.Game.Token;

import com.example.APIRollTheDice.Enum.CreatedItemType;
import com.example.APIRollTheDice.Model.DTO.GameDTO.TokenDTO.TokenDTO;
import com.example.APIRollTheDice.Model.DTO.GameDTO.TokenDTO.TokenPlacedDTO;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Token.Token;
import com.example.APIRollTheDice.Model.Obj.Game.Token.TokenPlaced;
import com.example.APIRollTheDice.Service.Game.Token.TokenService;
import com.example.APIRollTheDice.Service.User.UserCreationContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/token")
public class TokenControllers {
    private UserCreationContentService userCreationContentService;
    private TokenService tokenService;

    public TokenControllers(TokenService tokenService, UserCreationContentService userCreationContentService) {
        this.tokenService = tokenService;
        this.userCreationContentService = userCreationContentService;
    }

    // ------------------- Token        -------------------------

    @PostMapping("/CreateToken/{userId}")
    public ResponseEntity<TokenDTO> CreateToken(@PathVariable Long userId, @RequestBody TokenDTO tokenDTO) {
        Token token = tokenService.CreateToken(tokenService.TokenDTOToEntity(tokenDTO));

        UserCreationContentDTO userCreationContentDTO = new UserCreationContentDTO();
        userCreationContentDTO.setUserId(userId);
        userCreationContentDTO.setCreatedItemId(token.getId());
        userCreationContentDTO.setCreatedItemType(CreatedItemType.TOKEN);
        userCreationContentService.CreateUserCreationContent(userCreationContentService.UserCreationContentDTOToEntity(userCreationContentDTO));

        return ResponseEntity.ok(tokenService.TokenToDTO(token));
    }

    @PutMapping("/UpdateToken")
    public ResponseEntity<TokenDTO> UpdateToken(@RequestBody TokenDTO tokenDTO) {
        Token token = tokenService.UpdateToken(tokenService.TokenDTOToEntity(tokenDTO));
        return ResponseEntity.ok(tokenService.TokenToDTO(token));
    }

    @DeleteMapping("/DeleteToken/{tokenId}")
    public ResponseEntity<String> DeleteToken(@PathVariable Long tokenId) {
        tokenService.DeleteToken(tokenId);
        userCreationContentService.DeleteByCreatedItemId(tokenId, CreatedItemType.TOKEN);
        return ResponseEntity.ok("Token deleted successfully.");
    }

    @GetMapping("/GetTokenById/{tokenId}")
    public ResponseEntity<TokenDTO> GetTokenById(@PathVariable Long tokenId) {
        Token token = tokenService.GetTokenById(tokenId);
        return ResponseEntity.ok(tokenService.TokenToDTO(token));
    }

    @GetMapping("/GetAllByGameBundleId/{gameBundleId}")
    public ResponseEntity<List<TokenDTO>> GetAllByGameBundleId(@PathVariable Long gameBundleId) {
        List<Token> tokens = tokenService.GetAllByGameBundleId(gameBundleId);
        List<TokenDTO> tokenDTOS = tokens.stream().map(tokenService::TokenToDTO).toList();
        return ResponseEntity.ok(tokenDTOS);
    }


    // ------------------- Token Placed -------------------------

    @PostMapping("/CreateTokenPlaced")
    public ResponseEntity<TokenPlacedDTO> CreateToken(@RequestBody TokenPlacedDTO tokenPlacedDTO) {
        TokenPlaced token = tokenService.CreateTokenPlaced(tokenService.TokenPlacedDTOToEntity(tokenPlacedDTO));
        return ResponseEntity.ok(tokenService.TokenPlacedToDTO(token));
    }

    @PutMapping("/UpdateTokenPlaced")
    public ResponseEntity<TokenPlacedDTO> UpdateTokenPlaced(@RequestBody TokenPlacedDTO tokenDTO) {
        TokenPlaced token = tokenService.UpdateTokenPlaced(tokenService.TokenPlacedDTOToEntity(tokenDTO));
        return ResponseEntity.ok(tokenService.TokenPlacedToDTO(token));
    }

    @DeleteMapping("/DeleteTokenPlaced/{tokenId}")
    public ResponseEntity<String> DeleteTokenPlaced(@PathVariable Long tokenId) {
        tokenService.DeleteToken(tokenId);
        return ResponseEntity.ok("Token deleted successfully.");
    }


}
