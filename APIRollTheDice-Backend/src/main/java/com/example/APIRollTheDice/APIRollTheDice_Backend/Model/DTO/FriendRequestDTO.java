package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Enum.RequestStatus;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.FriendRequest;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;


import java.time.LocalTime;

public class FriendRequestDTO {

    private Long id;
    private User sender;
    private User receiver;
    private RequestStatus status;
    private LocalTime sentTime;


    public FriendRequestDTO() {
    }
    public FriendRequestDTO(Long id, User sender, User receiver, RequestStatus status, LocalTime sentTime) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
        this.sentTime = sentTime;
    }

    public static FriendRequestDTO from(FriendRequest friendRequest) {
          FriendRequestDTO dto = new FriendRequestDTO();
          dto.setId(friendRequest.getId());
          dto.setSender(friendRequest.getSender());
          dto.setReceiver(friendRequest.getReceiver());
          dto.setStatus(friendRequest.getStatus());
          dto.setSentTime(friendRequest.getSentTime());
          return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public LocalTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalTime sentTime) {
        this.sentTime = sentTime;
    }
}
