package com.example.APIRollTheDice.APIRollTheDice_Backend.Service.Game;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.BooksInterface.BookInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.PlayerInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.StorageInterface.StorageInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TemplateInterface.CustomObjectInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.User.UserRepository;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.PlayerMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.PlayerDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerInterface playerInterface;
    private final PlayerMapper playerMapper;

    private final StorageInterface storageInterface;
    private final BookInterface bookInterface;
    private final CustomObjectInterface customObjectInterface;
    private final UserRepository userRepository;


    public PlayerService(PlayerInterface playerInterface, PlayerMapper playerMapper, StorageInterface storageInterface, BookInterface bookInterface, CustomObjectInterface customObjectInterface, UserRepository userRepository) {
        this.playerInterface = playerInterface;
        this.playerMapper = playerMapper;
        this.storageInterface = storageInterface;
        this.bookInterface = bookInterface;
        this.customObjectInterface = customObjectInterface;
        this.userRepository = userRepository;
    }

    public Player CreatePlayer(Player player){
        return playerInterface.save(player);
    }

    public Player UpdatePlayer(Player player){
        Player existingPlayer = playerInterface.findById(player.getId()).orElseThrow(()-> new NotFoundException("Player not found"));
        existingPlayer.setName(player.getName());
        existingPlayer.setGame(player.getGame());
        existingPlayer.setCharacterModel(player.getCharacterModel());
        existingPlayer.setStorages(player.getStorages());
        existingPlayer.setLoreBook(player.getLoreBook());

        return playerInterface.save(existingPlayer);
    }

    public void DeletePlayer(Long id){
        Player existingPlayer = playerInterface.findById(id).orElseThrow(()-> new NotFoundException("Player not found"));
        playerInterface.delete(existingPlayer);
    }

    public Player GetPlayerById(Long id){
        return playerInterface.findById(id).orElseThrow(()-> new NotFoundException("Player not found"));
    }

    public List<Player> GetPlayersByUserId(Long userId){
        return playerInterface.findByUser_Id(userId);
    }

    public PlayerDTO PlayerToDTO(Player player) {
        return playerMapper.toDTO(player);
    }

    public Player DTOToPlayer(PlayerDTO playerDTO) {
        Player player = playerMapper.toEntity(playerDTO);

        if (playerDTO.getIdLoreBook() != null) {
            player.setLoreBook(bookInterface.findById(playerDTO.getIdLoreBook()).orElseThrow(() -> new NotFoundException("Book not found")));
        }

        if (playerDTO.getIdCharacterModel() != null) {
            player.setCharacterModel(customObjectInterface.findById(playerDTO.getIdCharacterModel()).orElseThrow(() -> new NotFoundException("Character Model not found")));
        }

        if (playerDTO.getIdUser() != null) {
            player.setUser(userRepository.findById(playerDTO.getIdUser()).orElseThrow(() -> new NotFoundException("User not found")));
        }

        if (playerDTO.getIdStorages() != null) {
            player.setStorages(storageInterface.findAllById(playerDTO.getIdStorages()));
        }

        return player;


    }




}
