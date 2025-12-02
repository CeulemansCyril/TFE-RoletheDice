package com.example.APIRollTheDice.Controllers.User;

import com.example.APIRollTheDice.Model.DTO.UserDTo.UserCreationContentDTO;
import com.example.APIRollTheDice.Model.DTO.UserDTo.UserDTO;
import com.example.APIRollTheDice.Model.Obj.User.User;
import com.example.APIRollTheDice.Model.Obj.User.UserCreationContent;
import com.example.APIRollTheDice.Service.User.UserCreationContentService;
import com.example.APIRollTheDice.Service.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class UserControllers {
    private final UserService userService;
    private final UserCreationContentService userCreationContentService;

    public UserControllers(UserService userService, UserCreationContentService userCreationContentService) {
        this.userService = userService;
        this.userCreationContentService = userCreationContentService;
    }

    // ------------------- User                -------------------------

    @PutMapping("/UpdateUser")
    public ResponseEntity<UserDTO> UpdateMap(@RequestBody UserDTO userDTO){
        User user = userService.updateUser(userService.DTOToUser(userDTO));
        return ResponseEntity.ok(userService.UserToDTO(user));
    }

    @DeleteMapping("/DeleteUser/{userID}")
    public ResponseEntity<String> DeleteUser(@PathVariable Long userID) {
        userService.deleteUser(userID);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @GetMapping("/GetUserByID/{userID}")
    public ResponseEntity<UserDTO> GetUserByID(@PathVariable Long userID) {
        User user = userService.getUserById(userID);
        return ResponseEntity.ok(userService.UserToDTO(user));
    }

    @GetMapping("/GetUserByUsername/{username}")
    public ResponseEntity<UserDTO> GetUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(userService.UserToDTO(user));
    }

    @GetMapping("/GetUserByEmail/{email}")
    public ResponseEntity<UserDTO> GetUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(userService.UserToDTO(user));
    }

    @GetMapping("/GetAllUsers")
    public ResponseEntity<List<UserDTO>> GetAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOS = users.stream().map(userService::UserToDTO).toList();
        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/GetAllUsersActive")
    public ResponseEntity<List<UserDTO>> GetAllUsersActive() {
        List<User> users = userService.getAllUsersActive();
        List<UserDTO> userDTOS = users.stream().map(userService::UserToDTO).toList();
        return ResponseEntity.ok(userDTOS);
    }

    @PostMapping("/AddFriend/{userID}/{friendID}")
    public ResponseEntity<UserDTO> AddFriend(@PathVariable Long userID, @PathVariable Long friendID) {

        return ResponseEntity.ok(userService.UserToDTO(  userService.AddFriend(userID, friendID)));
    }

    @PostMapping("/BlockUser/{userID}/{blockUserID}")
    public ResponseEntity<UserDTO> BlockUser(@PathVariable Long userID, @PathVariable Long blockUserID) {

        return ResponseEntity.ok(userService.UserToDTO( userService.BlockUser(userID, blockUserID)));
    }

    @DeleteMapping("/RemoveFriend/{userID}/{friendID}")
    public ResponseEntity<UserDTO> RemoveFriend(@PathVariable Long userID, @PathVariable Long friendID) {
        return ResponseEntity.ok(userService.UserToDTO(userService.RemoveFriend(userID, friendID)));
    }

    @DeleteMapping("/UnblockUser/{userID}/{blockUserID}")
    public ResponseEntity<UserDTO> UnblockUser(@PathVariable Long userID, @PathVariable Long blockUserID) {
        return ResponseEntity.ok(userService.UserToDTO(userService.UnblockUser(userID, blockUserID)));
    }

    @GetMapping("/CountUsers")
    public ResponseEntity<Integer> CountUsers() {
        int count = userService.countUsers();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/SearchAvailableUsers")
    public ResponseEntity<List<UserDTO>> SearchAvailableUsers(@RequestParam String query, @RequestParam Long userID) {
        List<User> users = userService.searchAvailableUsers(userID,query );
        List<UserDTO> userDTOS = users.stream().map(userService::UserToDTO).toList();
        return ResponseEntity.ok(userDTOS);
    }



    // ------------------- UserCreationContent -------------------------

    @GetMapping("/GetAllUserCreationContentByUser/{userID}")
    public ResponseEntity<List<UserCreationContentDTO>> GetAllUserCreationContentByUser(@PathVariable Long userID) {
        List<UserCreationContent> content = userCreationContentService.GetAllUserCreationContentByUser(userID);
        List<UserCreationContentDTO> contentDTOS = content.stream().map(userCreationContentService::UserCreationContentToDTO).toList();

        return ResponseEntity.ok(contentDTOS);
    }

    @GetMapping("/GetUserCreationContentByID/{contentID}")
    public ResponseEntity<UserCreationContentDTO> GetUserCreationContentByID(@PathVariable Long contentID) {
        UserCreationContent content = userCreationContentService.getUserCreationContentById(contentID);
        return ResponseEntity.ok(userCreationContentService.UserCreationContentToDTO(content));
    }

    @DeleteMapping("/DeleteUserCreationContent/{contentID} ")
    public ResponseEntity<String> DeleteUserCreationContent(@PathVariable Long contentID) {
        userCreationContentService.DeleteUserCreationContent(contentID);
        return ResponseEntity.ok("User creation content deleted successfully.");
    }

    @DeleteMapping("/DeleteAllUserCreationContentByUser/{userID} ")
    public ResponseEntity<String> DeleteAllUserCreationContentByUser(@PathVariable Long userID) {
        userCreationContentService.DeleteAllUserCreationContentByUser(userID);
        return ResponseEntity.ok("All user creation content for the user deleted successfully.");
    }

}
