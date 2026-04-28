package com.example.APIRollTheDice.Mapper.User;

import com.example.APIRollTheDice.Model.DTO.UserDTo.UserDTO;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserIdentifantData;
import com.example.APIRollTheDice.Model.Obj.Agenda.AgendaEvent;
import com.example.APIRollTheDice.Model.Obj.Conversation.Conversation;
import com.example.APIRollTheDice.Model.Obj.Game.Game;
import com.example.APIRollTheDice.Model.Obj.Game.Player;
import com.example.APIRollTheDice.Model.Obj.User.User;
import com.example.APIRollTheDice.Model.Obj.User.UserCreationContent;
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
    @Mapping(source = "userCreationContent", target = "idUserCreationContent",qualifiedByName = "userCreationContentToIds")
    @Mapping(source = "agendaEvents", target = "idAgendaEvent", qualifiedByName = "eventToIds")
    UserDTO toDTO(User user);


    @Mapping(target = "players", ignore = true)
    @Mapping(target = "gamesAsAdmin", ignore = true)
    @Mapping(target = "gamesAsCreator", ignore = true)
    @Mapping(target = "userConversations", ignore = true)
    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "blockedUsers", ignore = true)
    @Mapping(target = "userCreationContent", ignore = true)
    @Mapping(target = "agendaEvents", ignore = true)
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
    default List<UserIdentifantData> mapFriendsToIds(List<User> list) {
        return list == null ? null : list.stream().map(user -> new UserIdentifantData(user.getId(), user.getUsername())).toList();
    }

    @Named("blockedUsersToIds")
    default List<UserIdentifantData> mapBlockedUsersToIds(List<User> list) {
        return list == null ? null : list.stream().map(user -> new UserIdentifantData(user.getId(), user.getUsername())).toList();
    }

    @Named("eventToIds")
    default  List<Long> mapAgendaEventToIds(List<AgendaEvent> list){
        return list == null ? null : list.stream().map(AgendaEvent::getId).toList();
    }

    @Named("userCreationContentToIds")
    default  List<Long> mapUserCreationContentToIds(List<UserCreationContent> list){
        return list == null ? null : list.stream().map(UserCreationContent::getId).toList();
    }
}
