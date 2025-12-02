package com.example.APIRollTheDice.Interface;

import com.example.APIRollTheDice.Model.Obj.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestInterface extends JpaRepository<FriendRequest, Long> {

    FriendRequest findBySenderIdAndReceiverId(Long senderId, Long receiverId);

    List<FriendRequest> findAllByReceiverId(Long receiverId);
    List<FriendRequest> findAllBySenderId(Long senderId);

    boolean existsBySenderIdAndReceiverId(Long senderId, Long receiverId);

    void deleteBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
