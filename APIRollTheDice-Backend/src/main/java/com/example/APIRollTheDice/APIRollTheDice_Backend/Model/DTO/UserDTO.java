package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Enum.RoleUser;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Conversation;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private List<User> friends = new ArrayList<>();

    private List<User> blockedUsers = new ArrayList<>();
    private boolean isDelete = false;

    private List<Conversation> userConversations = new ArrayList<>();

    public static UserDTO from(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setDisplayName(user.getDisplayName());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setBlocked(user.isBlocked());
        userDTO.setRoleUser(user.getRoleUser());
        userDTO.setOnline(user.isOnline());
        userDTO.setProfilePublic(user.isProfilePublic());
        userDTO.setFriends(user.getFriends());
        userDTO.setBlockedUsers(user.getBlockedUsers());
        userDTO.setDelete(user.isDeleted());
        userDTO.setUserConversations(user.getUserConversations());
        return userDTO;
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

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        this.isOnline = online;
    }

    public boolean isProfilePublic() {
        return profilePublic;
    }

    public void setProfilePublic(boolean profilePublic) {
        this.profilePublic = profilePublic;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<User> getBlockedUsers() {
        return blockedUsers;
    }

    public void setBlockedUsers(List<User> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public List<Conversation> getUserConversations() {
        return userConversations;
    }

    public void setUserConversations(List<Conversation> userConversations) {
        this.userConversations = userConversations;
    }
}
