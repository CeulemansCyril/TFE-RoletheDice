package com.example.APIRollTheDice.Service.Game;

import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.GameInterface.BooksInterface.BookInterface;
import com.example.APIRollTheDice.Interface.GameInterface.GameBundleInterface;
import com.example.APIRollTheDice.Interface.GameInterface.GameInterface;
import com.example.APIRollTheDice.Interface.GameInterface.LootTableInterface.LootTableInterface;
import com.example.APIRollTheDice.Interface.GameInterface.MapInterface.MapInterface;
import com.example.APIRollTheDice.Interface.GameInterface.MoneyInterface.CurrencyInterface;
import com.example.APIRollTheDice.Interface.GameInterface.TokenInterface.TokenInterface;
import com.example.APIRollTheDice.Interface.User.UserRepository;
import com.example.APIRollTheDice.Mapper.Game.GameBundleMapper;
import com.example.APIRollTheDice.Model.DTO.GameDTO.GameBundleDTO;
import com.example.APIRollTheDice.Model.Obj.Game.GameBundle;
import org.springframework.stereotype.Service;

@Service
public class GameBundleService {
    private final GameBundleInterface gameBundleInterface;
    private final GameBundleMapper gameBundleMapper;


    private final UserRepository userRepository;
    private final TokenInterface tokenInterface;
    private final CurrencyInterface currencyInterface;
    private final LootTableInterface lootTableInterface;
    private final MapInterface mapInterface;
    private final BookInterface bookInterface;
    private final GameInterface gameInterface;

    public GameBundleService(GameBundleInterface gameBundleInterface, GameBundleMapper gameBundleMapper, UserRepository userRepository, TokenInterface tokenInterface, CurrencyInterface currencyInterface, LootTableInterface lootTableInterface, MapInterface mapInterface, BookInterface bookInterface,GameInterface gameInterface) {
        this.gameBundleInterface = gameBundleInterface;
        this.gameBundleMapper = gameBundleMapper;

        this.userRepository = userRepository;
        this.tokenInterface = tokenInterface;
        this.currencyInterface = currencyInterface;
        this.lootTableInterface = lootTableInterface;
        this.mapInterface = mapInterface;
        this.bookInterface = bookInterface;
        this.gameInterface = gameInterface;
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

    public GameBundle GameBundleDTOToEntity(GameBundleDTO gameBundleDTO){
        GameBundle gameBundle =gameBundleMapper.toEntity(gameBundleDTO);

        gameBundle.setTokens(tokenInterface.findAllByGameBundle_id(gameBundleDTO.getId()));
        gameBundle.setMaps(mapInterface.findAllByGameBundle_Id(gameBundleDTO.getId()));
        gameBundle.setGames(gameInterface.findAllById(gameBundleDTO.getIdGame()));
        gameBundle.setBooks(bookInterface.findAllByGameBundle_Id(gameBundleDTO.getId()));
        gameBundle.setLootTables(lootTableInterface.findAllById(gameBundleDTO.getIdLootTables()));
        gameBundle.setCurrencies(currencyInterface.findAllByGameBundles_id(gameBundleDTO.getId()));
        gameBundle.setCreator(userRepository.findById(gameBundleDTO.getIdCreator()).orElseThrow(()-> new NotFoundException("User not found")));


        return gameBundle;
    }



}
