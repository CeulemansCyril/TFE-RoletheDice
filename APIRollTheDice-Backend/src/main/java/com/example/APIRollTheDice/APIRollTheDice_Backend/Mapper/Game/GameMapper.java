package com.example.APIRollTheDice.APIRollTheDice_Backend.Mapper.Game;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.GameDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books.Book;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Player;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Mapping(source = "creator.id", target = "idCreator")
    @Mapping(source = "players", target = "idPlayers", qualifiedByName = "playersToIds")
    @Mapping(source = "playAdmins", target = "idPlayAdmins", qualifiedByName = "usersToIds")
    @Mapping(source = "chatChanels", target = "idChatChanels", qualifiedByName = "chatChanelsToIds")
    @Mapping(source = "gameBundle.id", target = "idGameBundle")
    @Mapping(source = "activeMap.id", target = "idActiveMap")
    @Mapping(source = "books", target = "idBooks", qualifiedByName = "booksToIds")
    GameDTO toDTO(Game game);

    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "players", ignore = true)
    @Mapping(target = "playAdmins", ignore = true)
    @Mapping(target = "chatChanels", ignore = true)
    @Mapping(target = "gameBundle", ignore = true)
    @Mapping(target = "activeMap", ignore = true)
    @Mapping(target = "books", ignore = true)
    Game toEntity(GameDTO dto);



    @Named("playersToIds")
    default List<Long> mapPlayersToIds(List<Player> list) {
        return list == null ? null : list.stream().map(Player::getId).toList();
    }

    @Named("usersToIds")
    default List<Long> mapUsersToIds(List<User> list) {
        return list == null ? null : list.stream().map(User::getId).toList();
    }

    @Named("chatChanelsToIds")
    default List<Long> mapChatChanelsToIds(List<ChatChanel> list) {
        return list == null ? null : list.stream().map(ChatChanel::getId).toList();
    }

    @Named("booksToIds")
    default List<Long> mapBooksToIds(List<Book> list) {
        return list == null ? null : list.stream().map(Book::getId).toList();
    }
}

