package com.example.APIRollTheDice.Controllers.Game;


import com.example.APIRollTheDice.Model.DTO.GameDTO.GameDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Game;
import com.example.APIRollTheDice.Service.Game.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameControllers {
    private final GameService gameService;

    public GameControllers(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/CreateGame")
    public ResponseEntity<GameDTO> CreateMap( @RequestBody GameDTO gameDTO){
        Game game = gameService.CreateGame(gameService.GameDTOToEntity(gameDTO));
        return ResponseEntity.ok(gameService.GameToDTO(game));
    }

    @PutMapping("/UpdateGame")
    public ResponseEntity<GameDTO> UpdateMap(@RequestBody GameDTO gameDTO){
        Game game  = gameService.UpdateGame(gameService.GameDTOToEntity(gameDTO));
        return ResponseEntity.ok(gameService.GameToDTO(game));
    }

    @DeleteMapping("/DeleteGame/{gameID}")
    public ResponseEntity<String> DeleteGame(@PathVariable Long gameID) {
        gameService.DeleteGame(gameID);
        return ResponseEntity.ok("Map deleted successfully.");
    }

    @GetMapping("/GetGameById/{gameID}")
    public ResponseEntity<GameDTO> GetGameById(@PathVariable Long gameID) {
        Game game = gameService.GetGameById(gameID);
        return ResponseEntity.ok(gameService.GameToDTO(game));
    }



}
