package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Enum.RequestStatus;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.FriendRequestDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.User;
import jakarta.persistence.*;


import java.time.LocalTime;

@Entity
@Table(name = "FriendRequest")
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    @Column(nullable = false)
    private LocalTime sentTime;




    public FriendRequest() {

    }

    public FriendRequest(User sender, User receiver, RequestStatus status, LocalTime sentTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
        this.sentTime = sentTime;
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
