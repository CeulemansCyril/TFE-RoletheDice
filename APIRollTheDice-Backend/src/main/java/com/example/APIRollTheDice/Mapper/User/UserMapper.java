package com.example.APIRollTheDice.Mapper.User;

import com.example.APIRollTheDice.Model.DTO.UserDTo.UserDTO;
import com.example.APIRollTheDice.Model.Obj.Conversation;
import com.example.APIRollTheDice.Model.Obj.Game.Game;
import com.example.APIRollTheDice.Model.Obj.Game.Player;
import com.example.APIRollTheDice.Model.Obj.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "players", target = "idPlayers", qualifiedByName = "playersToIds")
    @Mapping(source = "gamesAsAdmin", target = "idGamesAsAdmin", qualifiedByName = "gamesAsAdminToIds")
    @Mapping(source = "gamesAsCreator", target = "idGamesAsCreator", qualifiedByName = "gamesAsCreatorToIds")
    @Mapping(source = "userConversations", target = "idConversations", qualifiedByName = "conversationsToIds")
    @Mapping(source = "friends", target = "idFriends", qualifiedByName = "friendsToIds")
    @Mapping(source = "blockedUsers", target = "idBlockedUsers", qualifiedByName = "blockedUsersToIds")
    @Mapping(source = "userCreationContent.id", target = "idUserCreationContent")
    UserDTO toDTO(User user);


    @Mapping(target = "players", ignore = true)
    @Mapping(target = "gamesAsAdmin", ignore = true)
    @Mapping(target = "gamesAsCreator", ignore = true)
    @Mapping(target = "userConversations", ignore = true)
    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "blockedUsers", ignore = true)
    @Mapping(target = "userCreationContent", ignore = true)
    User toEntity(UserDTO dto);


    @Named("playersToIds")
    default List<Long> mapPlayersToIds(List<Player> list) {
        return list == null ? null : list.stream().map(Player::getId).toList();
    }

    @Named("gamesAsAdminToIds")
    default List<Long> mapGamesAsAdminToIds(List<Game> list) {
        return list == null ? null : list.stream().map(Game::getId).toList();
    }

    @Named("gamesAsCreatorToIds")
    default List<Long> mapGamesAsCreatorToIds(List<Game> list) {
        return list == null ? null : list.stream().map(Game::getId).toList();
    }

    @Named("conversationsToIds")
    default List<Long> mapConversationsToIds(List<Conversation> list) {
        return list == null ? null : list.stream().map(Conversation::getId).toList();
    }

    @Named("friendsToIds")
    default List<Long> mapFriendsToIds(List<User> list) {
        return list == null ? null : list.stream().map(User::getId).toList();
    }

    @Named("blockedUsersToIds")
    default List<Long> mapBlockedUsersToIds(List<User> list) {
        return list == null ? null : list.stream().map(User::getId).toList();
    }
}
