package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.PlayerDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Player;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Storage.Storage;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

public interface PlayerMapper {
    @Mapping(source = "loreBook.id", target = "idLoreBook")
    @Mapping(source = "storages.id", target = "idStorages",qualifiedByName="storagesToIds")
    @Mapping(source = "characterModel.id", target = "idCharacterModel")
    @Mapping(source = "user.id", target = "idUser")
    @Mapping(source = "game.id", target = "idGame",qualifiedByName = "gameToIds")
    PlayerDTO toDTO(Player player);


    @Mapping(target = "loreBook", ignore = true)
    @Mapping(target = "storages", ignore = true)
    @Mapping(target = "characterModel", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "game", ignore = true)
    Player toEntity(PlayerDTO playerDTO);


    @Named("storagesToIds")
    default List<Long> mapStoragesToIds(List<Storage> list) {
        return list == null ? null : list.stream().map(Storage::getId).toList();
    }
    @Named("gameToIds")
    default List<Long> mapGameToIds(List<Game> list) {
        return list == null ? null : list.stream().map(Game::getId).toList();
    }

}
