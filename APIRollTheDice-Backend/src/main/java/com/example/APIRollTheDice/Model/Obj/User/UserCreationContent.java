package com.example.APIRollTheDice.Model.Obj.User;

import com.example.APIRollTheDice.Enum.CreatedItemType;
import jakarta.persistence.*;

@Entity
@Table(name = "user_creation_content")
public class UserCreationContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private CreatedItemType createdItemType;

    private Long createdItemId;

    public UserCreationContent(Long id, User user, Long createdItemId, CreatedItemType createdItemType) {
        this.id = id;
        this.user = user;
        this.createdItemId = createdItemId;
        this.createdItemType = createdItemType;
    }

    public UserCreationContent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CreatedItemType getCreatedItemType() {
        return createdItemType;
    }

    public void setCreatedItemType(CreatedItemType createdItemType) {
        this.createdItemType = createdItemType;
    }

    public Long getCreatedItemId() {
        return createdItemId;
    }

    public void setCreatedItemId(Long createdItemId) {
        this.createdItemId = createdItemId;
    }
}


