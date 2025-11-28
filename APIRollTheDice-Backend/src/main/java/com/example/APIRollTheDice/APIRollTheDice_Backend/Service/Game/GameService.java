package com.example.APIRollTheDice.APIRollTheDice_Backend.Service.Game;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.ChatInterface.ChatChanelInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.BooksInterface.BookInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.GameBundleInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.GameInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.MapInterface.MapInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.PlayerInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.User.UserRepository;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.GameMapper;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.GameDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameMapper gameMapper;
    private final GameInterface gameInterface;

    private final GameBundleInterface gameBundleInterface;
    private final BookInterface bookInterface;
    private final PlayerInterface playerInterface;
    private final UserRepository  userRepository;
    private final ChatChanelInterface chatChanelInterface;
    private final MapInterface mapInterface;

    public GameService(GameMapper gameMapper, GameInterface gameInterface, GameBundleInterface gameBundleInterface, BookInterface bookInterface, PlayerInterface playerInterface, UserRepository userRepository, MapInterface mapInterface, ChatChanelInterface chatChanelInterface) {
        this.gameMapper = gameMapper;
        this.gameInterface = gameInterface;
        this.gameBundleInterface = gameBundleInterface;
        this.bookInterface = bookInterface;
        this.playerInterface = playerInterface;
        this.userRepository = userRepository;
        this.mapInterface = mapInterface;
        this.chatChanelInterface = chatChanelInterface;
    }

    public Game CreateGame(Game game){
        return gameInterface.save(game);
    }

    public Game UpdateGame(Game game){
        Game existing = gameInterface.findById(game.getId()).orElseThrow(()-> new NotFoundException("Game not found"));

        existing.setGameBundle(game.getGameBundle());
        existing.setBooks(game.getBooks());
        existing.setName(game.getName());
        existing.setActiveMap(game.getActiveMap());
        existing.setChatChanels(game.getChatChanels());
        existing.setPlayAdmins(game.getPlayAdmins());
        existing.setPlayers(game.getPlayers());

        return gameInterface.save(existing);
    }

    public void DeleteGame(Long id){
        if(gameInterface.existsById(id))gameInterface.deleteById(id);
        else throw new NotFoundException("Game not found");
    }

    public Game GetGameById(Long id){
        return gameInterface.findById(id).orElseThrow(()-> new NotFoundException("Game not found"));
    }

    public GameDTO GameToDTO(Game game){
        return gameMapper.toDTO(game);
    }

    public Game GameDTOToEntity(GameDTO gameDTO){
        Game game = gameMapper.toEntity(gameDTO);

        game.setPlayers(playerInterface.findAllById(gameDTO.getIdPlayers()));
        game.setGameBundle(gameBundleInterface.findById(gameDTO.getIdGameBundle()).orElseThrow(()-> new NotFoundException("GameBundle not found")));
        game.setBooks(bookInterface.findAllByGame_Id(gameDTO.getId()));
        game.setCreator(userRepository.findById(gameDTO.getIdCreator()).orElseThrow(()-> new NotFoundException("Creator Game not found")));
        game.setChatChanels(chatChanelInterface.findAllByGame_Id(gameDTO.getId()));
        game.setActiveMap(mapInterface.findById(gameDTO.getIdActiveMap()).orElseThrow(()-> new NotFoundException("Map not found")));


        return game;

    }



}
