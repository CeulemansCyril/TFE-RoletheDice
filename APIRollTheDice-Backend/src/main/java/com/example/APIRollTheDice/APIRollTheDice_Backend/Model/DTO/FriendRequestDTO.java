package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Enum.RequestStatus;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.FriendRequest;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;


import java.time.LocalTime;

public class FriendRequestDTO {

    private Long id;
    private Long idSender;
    private Long idReceiver;
    private RequestStatus status;
    private LocalTime sentTime;


    public FriendRequestDTO() {
    }

    public FriendRequestDTO(Long id, Long idSender, RequestStatus status, Long idReceiver, LocalTime sentTime) {
        this.id = id;
        this.idSender = idSender;
        this.status = status;
        this.idReceiver = idReceiver;
        this.sentTime = sentTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSender() {
        return idSender;
    }

    public void setIdSender(Long idSender) {
        this.idSender = idSender;
    }

    public Long getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(Long idReceiver) {
        this.idReceiver = idReceiver;
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
