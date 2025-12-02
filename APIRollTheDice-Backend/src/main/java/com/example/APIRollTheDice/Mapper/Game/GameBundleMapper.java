package com.example.APIRollTheDice.Mapper.Game;

import com.example.APIRollTheDice.Model.DTO.GameDTO.GameBundleDTO;
import com.example.APIRollTheDice.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.Model.Obj.Game.Game;
import com.example.APIRollTheDice.Model.Obj.Game.GameBundle;
import com.example.APIRollTheDice.Model.Obj.Game.LootTable.LootTable;
import com.example.APIRollTheDice.Model.Obj.Game.Map.Map;
import com.example.APIRollTheDice.Model.Obj.Game.Money.Currency;
import com.example.APIRollTheDice.Model.Obj.Game.Token.Token;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameBundleMapper {


    @Mapping(source = "creator.id",target = "idCreator")
    @Mapping(source = "games",target = "idGame",qualifiedByName ="gameToIds")
    @Mapping(source = "tokens",target = "idTokens",qualifiedByName = "tokenToIds")
    @Mapping(source = "books",target = "idBooks", qualifiedByName = "bookToIds")
    @Mapping(source = "currencies",target = "idCurrencies",qualifiedByName = "currenciesToIds")
    @Mapping(source = "lootTables",target = "idLootTables",qualifiedByName = "lootTablesToIds")
    @Mapping(source = "maps",target = "idMap", qualifiedByName = "mapToIds")
    GameBundleDTO toDTO(GameBundle gameBundle);


    @Mapping(target = "creator",ignore = true)
    @Mapping(target = "games",ignore = true)
    @Mapping(target = "tokens",ignore = true)
    @Mapping(target = "books",ignore = true)
    @Mapping(target = "currencies",ignore = true)
    @Mapping(target = "lootTables",ignore = true)
    @Mapping(target = "maps",ignore = true)
    GameBundle toEntity(GameBundleDTO gameBundleDTO);


    @Named("gameToIds")
    default List<Long> mapGameToIds(List<Game> list) {
        return list == null ? null : list.stream().map(Game::getId).toList();
    }

    @Named("tokenToIds")
    default List<Long> mapTokenToIds(List<Token> list) {
        return list == null ? null : list.stream().map(Token::getId).toList();
    }

    @Named("bookToIds")
    default List<Long> mapBookToIds(List<Book> list) {
        return list == null ? null : list.stream().map(Book::getId).toList();
    }

    @Named("mapToIds")
    default List<Long> mapMapToIds(List<Map> list) {
        return list == null ? null : list.stream().map(Map::getId).toList();
    }

    @Named("lootTablesToIds")
    default List<Long> mapLootTablesToIds(List<LootTable> list) {
        return list == null ? null : list.stream().map(LootTable::getId).toList();
    }

    @Named("currenciesToIds")
    default List<Long> mapCurrenciesToIds(List<Currency> list) {
        return list == null ? null : list.stream().map(Currency::hashCode).map(Long::valueOf).toList();
    }
}
