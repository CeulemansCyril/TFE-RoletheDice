package com.example.APIRollTheDice.Service.User;

import com.example.APIRollTheDice.Enum.RoleUser;
import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.ConversationInterface;
import com.example.APIRollTheDice.Interface.GameInterface.GameInterface;
import com.example.APIRollTheDice.Interface.GameInterface.PlayerInterface;
import com.example.APIRollTheDice.Interface.User.UserCreationContentInterface;
import com.example.APIRollTheDice.Interface.User.UserRepository;
import com.example.APIRollTheDice.Mapper.User.UserMapper;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserDTO;
import com.example.APIRollTheDice.Model.Obj.User.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PlayerInterface playerRepository;
    private final GameInterface gameRepository;
    private final ConversationInterface conversationRepository;
    private final UserCreationContentInterface userCreationContentRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper,
                       PlayerInterface playerRepository,
                       GameInterface gameRepository,
                       ConversationInterface conversationRepository,
                          UserCreationContentInterface userCreationContentRepository
    )
    {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.conversationRepository = conversationRepository;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userCreationContentRepository = userCreationContentRepository;
    }



    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersActive() {
        return userRepository.findAllByIsDeletedFalse();
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null && user.isDeleted()) {
            return null;
        }
        return user;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.isDeleted()) {
            return null;
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.isDeleted()) {
            return null;
        }
        return user;
    }


    public User createUser(User user) {

        if (userRepository.existsByUsername(user.getUsername())){
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("Email already used");
        }

        return userRepository.save(user);
    }

    public User updateUser( User userDetails) {
        User user = userRepository.findById(userDetails.getId()).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setDisplayName(userDetails.getDisplayName());
            user.setDateOfBirth(userDetails.getDateOfBirth());
            user.setBlocked(userDetails.isBlocked());
            user.setRoleUser(userDetails.getRoleUser());
            user.setOnline(userDetails.isOnline());
            user.setProfilePublic(userDetails.isProfilePublic());
            user.setFriends(userDetails.getFriends());
            user.setBlockedUsers(userDetails.getBlockedUsers());
            user.setDeleted(userDetails.isDeleted());
            user.setUserCreationContent(userDetails.getUserCreationContent());

            return userRepository.save(user);
        }
        return null;
    }
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setEmail("---------");
            user.setUsername("---------");
            user.setPassword("---------");
            user.setDisplayName("---------");
            user.setDateOfBirth(null);
            user.setBlocked(false);
            user.setRoleUser(RoleUser.DISABLED);
            user.setOnline(false);
            user.setProfilePublic(false);
            user.setFriends(null);
            user.setBlockedUsers(null);
            user.setDeleted(true);
            userRepository.save(user);
            return true;
        }
        return  false ;
    }

    public User AddFriend(Long userId, Long friendId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found with in AddFriend" ));
        User friend = userRepository.findById(friendId).orElseThrow(()-> new NotFoundException("Friend not found in AddFriend "   ));

        if((user.AddFriend(friend))  &&  (friend.AddFriend(user))){
            userRepository.save(user);
            userRepository.save(friend);
        }

        return user;

    }

    public User RemoveFriend(Long userId, Long friendId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found with in RemoveFriend" ));
        User friend = userRepository.findById(friendId).orElseThrow(()-> new NotFoundException("Friend not found in RemoveFriend "   ));

        if ((user.RemoveFriend(friend)) && (friend.RemoveFriend(user))) {
            user.RemoveFriend(friend);
            friend.RemoveFriend(user);

            userRepository.save(user);
            userRepository.save(friend);
        }

        return user;
    }

    public User BlockUser(Long userId, Long blockedUserId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found with in BlockUser" ));
        User blockedUser = userRepository.findById(blockedUserId).orElseThrow(()-> new NotFoundException("blockedUser not found in BlockUser "   ));
        if ((user.BlockUser(blockedUser)) && (blockedUser.BlockUser(user)) ) {
            userRepository.save(user);
            userRepository.save(blockedUser);
        }

        return user;
    }
    public User UnblockUser(Long userId, Long blockedUserId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found with in BlockUser" ));
        User blockedUser = userRepository.findById(blockedUserId).orElseThrow(()-> new NotFoundException("blockedUser not found in BlockUser "   ));
        if ((user.UnblockUser(blockedUser)) && (blockedUser.UnblockUser(user))) {
            userRepository.save(user);
            userRepository.save(blockedUser);
        }

        return user;
    }

    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    public boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    public boolean isOnline(Long id) {
        return userRepository.existsByIdAndIsOnlineTrue(id);
    }

    public int countUsers() {
        return (int) userRepository.count();
    }

    public List<User> searchAvailableUsers(Long userId, String seach) {
        return userRepository.searchAvailableUsers(userId, seach);
    }


    public UserDTO UserToDTO(User user) {
       return userMapper.toDTO(user);
    }

    public User DTOToUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        if (userDTO.getIdPlayers() != null) {
            user.setPlayers(playerRepository.findAllById(userDTO.getIdPlayers()));
        }
        if (userDTO.getIdGamesAsAdmin() != null) {
            user.setGamesAsAdmin(gameRepository.findAllById(userDTO.getIdGamesAsAdmin()));
        }
        if (userDTO.getIdGamesAsCreator() != null) {
            user.setGamesAsCreator(gameRepository.findAllById(userDTO.getIdGamesAsCreator()));
        }
        if (userDTO.getIdConversations() != null) {
            user.setUserConversations(conversationRepository.findAllById(userDTO.getIdConversations()));
        }
        if (userDTO.getIdFriends() != null) {
            user.setFriends(userRepository.findAllById(userDTO.getIdFriends()));
        }
        if (userDTO.getIdBlockedUsers() != null) {
            user.setBlockedUsers(userRepository.findAllById(userDTO.getIdBlockedUsers()));
        }
        if (userDTO.getIdUserCreationContent() != null) {
            user.setUserCreationContent(userCreationContentRepository.findById(userDTO.getIdUserCreationContent()).orElse(null));
        }
        return user;
    }

}
