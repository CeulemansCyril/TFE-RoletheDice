package com.example.APIRollTheDice.APIRollTheDice_Backend.Service;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Exception.NotFoundException;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.FriendRequestInterface;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.FriendRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestService {
    private static FriendRequestInterface friendRequestInterface;

    public FriendRequestService(FriendRequestInterface friendRequestInterface) {
        FriendRequestService.friendRequestInterface = friendRequestInterface;
    }

    public FriendRequest createFriendRequest(FriendRequest friendRequest) {
        return friendRequestInterface.save(friendRequest);
    }
    public List<FriendRequest> getAllFriendRequestBySender(Long id) {
        return friendRequestInterface.findAllBySenderId(id);
    }
    public List<FriendRequest> getAllFriendRequestByReceiver(Long id) {
        return friendRequestInterface.findAllByReceiverId(id);
    }

    public FriendRequest getFriendRequestById(Long id) {
        return friendRequestInterface.findById(id).orElseThrow(()-> new NotFoundException("Friend request not found "));
    }

    public FriendRequest getFriendRequestBySenderAndReceiver(Long senderId, Long receiverId) {
        return friendRequestInterface.findBySenderIdAndReceiverId(senderId, receiverId);
    }

    public void deleteFriendRequest(Long id) {
        friendRequestInterface.deleteById(id);
    }

    public FriendRequest updateFriendRequest(FriendRequest friendRequest) {
        return friendRequestInterface.save(friendRequest);
    }
    public boolean existsBySenderIdAndReceiverId(Long senderId, Long receiverId) {
        return friendRequestInterface.existsBySenderIdAndReceiverId(senderId, receiverId);
    }

}
