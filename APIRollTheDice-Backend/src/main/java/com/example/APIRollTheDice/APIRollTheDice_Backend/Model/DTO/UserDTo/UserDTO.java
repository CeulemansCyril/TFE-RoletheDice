package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.UserDTo;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Enum.RoleUser;

import java.time.LocalDate;
import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String displayName;
    private LocalDate dateOfBirth;
    private boolean blocked = false;
    private RoleUser roleUser;
    private boolean isOnline = false;
    private boolean profilePublic = true;
    private List<Long> idPlayers;
    private List<Long> idGamesAsAdmin;
    private List<Long> idGamesAsCreator;
    private List<Long> idConversations;
    private List<Long> idFriends;
    private List<Long> idBlockedUsers;
    private boolean isDeleted = false;
    private Long idUserCreationContent;

    public UserDTO(Long id, String username, String email, String password, String displayName, LocalDate dateOfBirth, boolean blocked, RoleUser roleUser, boolean isOnline, boolean profilePublic, List<Long> idPlayers, List<Long> idGamesAsAdmin, List<Long> idGamesAsCreator, List<Long> idConversations, List<Long> idFriends, List<Long> idBlockedUsers, boolean isDeleted, Long idUserCreationContent) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
        this.blocked = blocked;
        this.roleUser = roleUser;
        this.isOnline = isOnline;
        this.profilePublic = profilePublic;
        this.idPlayers = idPlayers;
        this.idGamesAsAdmin = idGamesAsAdmin;
        this.idGamesAsCreator = idGamesAsCreator;
        this.idConversations = idConversations;
        this.idFriends = idFriends;
        this.idBlockedUsers = idBlockedUsers;
        this.isDeleted = isDeleted;
        this.idUserCreationContent = idUserCreationContent;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public boolean isProfilePublic() {
        return profilePublic;
    }

    public void setProfilePublic(boolean profilePublic) {
        this.profilePublic = profilePublic;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public List<Long> getIdGamesAsAdmin() {
        return idGamesAsAdmin;
    }

    public void setIdGamesAsAdmin(List<Long> idGamesAsAdmin) {
        this.idGamesAsAdmin = idGamesAsAdmin;
    }

    public List<Long> getIdPlayers() {
        return idPlayers;
    }

    public void setIdPlayers(List<Long> idPlayers) {
        this.idPlayers = idPlayers;
    }

    public List<Long> getIdGamesAsCreator() {
        return idGamesAsCreator;
    }

    public void setIdGamesAsCreator(List<Long> idGamesAsCreator) {
        this.idGamesAsCreator = idGamesAsCreator;
    }

    public List<Long> getIdConversations() {
        return idConversations;
    }

    public void setIdConversations(List<Long> idConversations) {
        this.idConversations = idConversations;
    }

    public List<Long> getIdFriends() {
        return idFriends;
    }

    public void setIdFriends(List<Long> idFriends) {
        this.idFriends = idFriends;
    }

    public List<Long> getIdBlockedUsers() {
        return idBlockedUsers;
    }

    public void setIdBlockedUsers(List<Long> idBlockedUsers) {
        this.idBlockedUsers = idBlockedUsers;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Long getIdUserCreationContent() {
        return idUserCreationContent;
    }

    public void setIdUserCreationContent(Long idUserCreationContent) {
        this.idUserCreationContent = idUserCreationContent;
    }
}
