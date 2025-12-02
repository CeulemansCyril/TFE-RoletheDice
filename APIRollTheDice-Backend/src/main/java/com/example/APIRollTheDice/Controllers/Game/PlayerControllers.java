package com.example.APIRollTheDice.Controllers.Game;

import com.example.APIRollTheDice.Model.DTO.GameDTO.PlayerDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Player;
import com.example.APIRollTheDice.Service.Game.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerControllers {
    private final PlayerService playerService;

    public PlayerControllers(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/createPlayer")
    public ResponseEntity<PlayerDTO> createPlayer(PlayerDTO playerDTO) {
        Player player = playerService.CreatePlayer(playerService.DTOToPlayer(playerDTO));
        return ResponseEntity.ok(playerService.PlayerToDTO(player));
    }

    @PutMapping("/updatePlayer")
    public ResponseEntity<PlayerDTO> updatePlayer(PlayerDTO playerDTO) {
        Player player = playerService.UpdatePlayer(playerService.DTOToPlayer(playerDTO));
        return ResponseEntity.ok(playerService.PlayerToDTO(player));
    }

    @DeleteMapping("/deletePlayer/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id) {
        playerService.DeletePlayer(id);
        return ResponseEntity.ok("Player deleted successfully.");
    }

    @GetMapping("/getPlayerById/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        Player player = playerService.GetPlayerById(id);
        return ResponseEntity.ok(playerService.PlayerToDTO(player));
    }

    @GetMapping("/getPlayersByIdGame/{idGame}")
    public ResponseEntity<List<PlayerDTO>> getPlayerByIdGame(@PathVariable Long idGame) {
        List<Player> players = playerService.GetPlayersByGameId(idGame);
        List<PlayerDTO> playerDTOs = players.stream()
                .map(playerService::PlayerToDTO)
                .toList();
        return ResponseEntity.ok(playerDTOs);
    }

    @GetMapping("/getPlayersByIdUser/{idUser}")
    public ResponseEntity<List<PlayerDTO>> getPlayersByIdUser(@PathVariable Long idUser) {
        List<Player> players = playerService.GetPlayersByUserId(idUser);
        List<PlayerDTO> playerDTOs = players.stream()
                .map(playerService::PlayerToDTO)
                .toList();
        return ResponseEntity.ok(playerDTOs);
    }




}
