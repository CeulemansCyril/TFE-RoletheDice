package com.example.APIRollTheDice.Service;

import com.example.APIRollTheDice.Exception.NotFoundException;
import com.example.APIRollTheDice.Interface.FriendRequestInterface;
import com.example.APIRollTheDice.Interface.User.UserRepository;
import com.example.APIRollTheDice.Mapper.FriendRequestMapper;
import com.example.APIRollTheDice.Model.DTO.FriendRequestDTO;
import com.example.APIRollTheDice.Model.Obj.FriendRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestService {
    private final FriendRequestInterface friendRequestInterface;
    private final FriendRequestMapper friendRequestMapper;

    private final UserRepository userRepository;

    public FriendRequestService(FriendRequestInterface friendRequestInterface, FriendRequestMapper friendRequestMapper, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.friendRequestInterface = friendRequestInterface;
        this.friendRequestMapper = friendRequestMapper;

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

    public FriendRequest FriendRequestDTOToEntity(FriendRequestDTO dto) {
        FriendRequest friendRequest = friendRequestMapper.toEntity(dto);
        friendRequest.setSender(userRepository.findById(dto.getIdSender()).orElseThrow(() -> new NotFoundException("Sender not found")));
        friendRequest.setReceiver(userRepository.findById(dto.getIdReceiver()).orElseThrow(() -> new NotFoundException("Receiver not found")));
        return friendRequest;
    }

    public FriendRequestDTO FriendRequestEntityToDTO(FriendRequest friendRequest) {
        return friendRequestMapper.toDTO(friendRequest);
    }

}
