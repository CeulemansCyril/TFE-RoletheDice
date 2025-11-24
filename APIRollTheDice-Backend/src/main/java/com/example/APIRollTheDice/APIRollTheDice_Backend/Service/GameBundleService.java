package com.example.APIRollTheDice.APIRollTheDice_Backend.Service;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.BooksInterface.BookInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.GameBundleInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.LootTableInterface.LootTableInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.MapInterface.MapInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.MoneyInterface.CurrencyInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.PlayerInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.GameInterface.TokenInterface.TokenInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.UserRepository;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game.GameBundleMapper;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.GameBundleDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.GameBundle;
import org.springframework.stereotype.Service;

@Service
public class GameBundleService {
    private final GameBundleInterface gameBundleInterface;
    private final GameBundleMapper gameBundleMapper;

    private final PlayerInterface playerInterface;
    private final UserRepository userRepository;
    private final TokenInterface tokenInterface;
    private final CurrencyInterface currencyInterface;
    private final LootTableInterface lootTableInterface;
    private final MapInterface mapInterface;
    private final BookInterface bookInterface;

    public GameBundleService(GameBundleInterface gameBundleInterface, GameBundleMapper gameBundleMapper, PlayerInterface playerInterface, UserRepository userRepository, TokenInterface tokenInterface, CurrencyInterface currencyInterface, LootTableInterface lootTableInterface, MapInterface mapInterface, BookInterface bookInterface) {
        this.gameBundleInterface = gameBundleInterface;
        this.gameBundleMapper = gameBundleMapper;
        this.playerInterface = playerInterface;
        this.userRepository = userRepository;
        this.tokenInterface = tokenInterface;
        this.currencyInterface = currencyInterface;
        this.lootTableInterface = lootTableInterface;
        this.mapInterface = mapInterface;
        this.bookInterface = bookInterface;
    }

    public GameBundle CreateGameBundle(GameBundle gameBundle){
        return gameBundleInterface.save(gameBundle);
    }

    public GameBundle UpdateGameBundle(GameBundle gameBundle){
        GameBundle existing = gameBundleInterface.findById(gameBundle.getId()).orElseThrow(()-> new NotFoundException("GameBundle not found"));

        existing.setGames(gameBundle.getGames());
        existing.setName(gameBundle.getName());
        existing.setBooks(gameBundle.getBooks());
        existing.setCurrencies(gameBundle.getCurrencies());
        existing.setMaps(gameBundle.getMaps());
        existing.setLootTables(gameBundle.getLootTables());
        existing.setTokens(gameBundle.getTokens());

        return gameBundleInterface.save(existing);
    }

    public void DeleteGameBundle(Long id){
        if(gameBundleInterface.existsById(id))gameBundleInterface.deleteById(id);
        else throw new  NotFoundException("GameBundle not found");
    }

    public GameBundle GetGameBundleById(Long id){
        return gameBundleInterface.findById(id).orElseThrow(()-> new NotFoundException("GameBundle not found"));
    }

    public GameBundleDTO  GameBundleToDTO(GameBundle gameBundle){
        return gameBundleMapper.toDTO(gameBundle);
    }



}
