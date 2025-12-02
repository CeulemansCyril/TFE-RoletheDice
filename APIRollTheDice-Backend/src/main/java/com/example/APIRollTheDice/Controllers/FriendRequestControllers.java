package com.example.APIRollTheDice.Controllers;

import com.example.APIRollTheDice.Enum.RequestStatus;
import com.example.APIRollTheDice.Exception.AlreadyExistsException;
import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Model.Obj.FriendRequest;
import com.example.APIRollTheDice.Model.Obj.User.User;
import com.example.APIRollTheDice.Service.FriendRequestService;
import com.example.APIRollTheDice.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/friend-requests")
public class FriendRequestControllers {

    private final FriendRequestService friendRequestService ;

    private final UserService userService;

    @Autowired
    public FriendRequestControllers(FriendRequestService friendRequestService, UserService userService) {
        this.friendRequestService = friendRequestService;
        this.userService = userService;
    }

    @PostMapping("/create/{senderId}/{receiverId}")
    public ResponseEntity<String> createFriendRequest(@PathVariable Long senderId,@PathVariable Long receiverId) {
        if (!friendRequestService.existsBySenderIdAndReceiverId(senderId, receiverId)) {
            User sender = userService.getUserById(senderId);
            User receiver = userService.getUserById(receiverId);
            if (sender == null || receiver == null) {
                FriendRequest friendRequest = new FriendRequest();
                friendRequest.setSender(sender);
                friendRequest.setReceiver(receiver);
                friendRequest.setStatus(RequestStatus.PENDING);
                friendRequest.setSentTime(java.time.LocalTime.now());
                friendRequestService.createFriendRequest(friendRequest);

            }else {
                throw new NotFoundException("User not found with the provided ID.");
            }
        } else {
            throw new AlreadyExistsException("Friend request already exists between these users.");
        }
        return ResponseEntity.ok("Friend request sent successfully.");
    }

    @PostMapping("/accept/{IdRequest}")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable Long IdRequest) {
         FriendRequest friendRequest = friendRequestService.getFriendRequestById(IdRequest);

         User sender = friendRequest.getSender();
         User receiver = friendRequest.getReceiver();

         userService.AddFriend(sender.getId(), receiver.getId());
         userService.AddFriend(receiver.getId(), sender.getId());

         friendRequest.setStatus(RequestStatus.ACCEPTED);
            friendRequestService.updateFriendRequest(friendRequest);

        return ResponseEntity.ok("Friend request accepted successfully.");
    }

    @PostMapping("/reject/{IdRequest}")
    public ResponseEntity<String> rejectFriendRequest(@PathVariable Long IdRequest) {
        FriendRequest friendRequest = friendRequestService.getFriendRequestById(IdRequest);

        friendRequest.setStatus(RequestStatus.REJECTED);

        friendRequestService.updateFriendRequest(friendRequest);

        return ResponseEntity.ok("Friend request rejected successfully.");
    }


}
