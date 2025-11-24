package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Enum.RoleUser;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.UserFriendException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.UserDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Conversation;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = true)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private boolean blocked = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleUser roleUser;

    @Column(nullable = false)
    private boolean isOnline = false;

    @Column(nullable = false)
    private boolean profilePublic = true;

    @JsonIgnore
    @ManyToMany(mappedBy = "players")
    private Set<Game> gamesAsPlayer;

    @JsonIgnore
    @ManyToMany(mappedBy = "playAdmins")
    private Set<Game> gamesAsAdmin;

    @JsonIgnore
    @ManyToMany(mappedBy = "creator")
    private Set<Game> gamesAsCreator;


    @ManyToMany
    @JoinTable(
            name = "user_conversations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conversation_id")
    )
    private List<Conversation> userConversations = new ArrayList<>();


    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friends = new ArrayList<>();

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "user_blocked",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "blocked_user_id")
    )
    private List<User> blockedUsers = new ArrayList<>();

    @Column(nullable = false)
    private boolean isDeleted = false;

    public static User from(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setDisplayName(userDTO.getDisplayName());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setBlocked(userDTO.isBlocked());
        user.setRoleUser(userDTO.getRoleUser());
        user.setOnline(userDTO.isOnline());
        user.setProfilePublic(userDTO.isProfilePublic());
        user.setFriends(userDTO.getFriends());
        user.setBlockedUsers(userDTO.getBlockedUsers());
        user.setDeleted(userDTO.isDelete());
        user.setUserConversations(userDTO.getUserConversations());
        return user;
    }

    public User() {
    }

    public User(Long id, String username, String email, String password, String displayName, LocalDate dateOfBirth, RoleUser roleUser, boolean blocked, boolean isOnline, boolean profilePublic, List<User> friends, List<User> blockedUsers,
           List<Conversation> userConversations
            ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.dateOfBirth = dateOfBirth;
        this.roleUser = roleUser;
        this.blocked = blocked;
        this.isOnline = isOnline;
        this.profilePublic = profilePublic;
        this.friends = friends;
        this.blockedUsers = blockedUsers;
        this.userConversations = userConversations;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<Conversation> getUserConversations() {
        return userConversations;
    }

    public void setUserConversations(List<Conversation> userConversations) {
        this.userConversations = userConversations;
    }

    public boolean AddFriend(User friend) {

        if (friends.contains(friend)) {
            throw new UserFriendException("User is already a friend");
        }else{
            friends.add(friend);
            return true;
        }
    }

    public boolean RemoveFriend(User friend) {
        if(!friends.contains(friend))  {
            throw new UserFriendException("User is not a friend");
        }else {
            friends.remove(friend);
            return true;
        }
    }

    public boolean BlockUser(User user) {
        if (blockedUsers.contains(user)) {
            throw new UserFriendException("User is already blocked");
        } else {
            blockedUsers.add(user);
            return true;
        }


    }

    public boolean UnblockUser(User user) {

    if (!blockedUsers.contains(user)) {
            throw new UserFriendException("User is not blocked");
        } else {
            blockedUsers.remove(user);
            return true;
        }

    }
}
