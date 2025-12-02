package com.example.APIRollTheDice.Model.DTO.UserDTo;

import com.example.APIRollTheDice.Enum.CreatedItemType;

public class UserCreationContentDTO {
    private Long id;
    private Long userId;
    private Long createdItemId;
    private CreatedItemType createdItemType;

    public UserCreationContentDTO(Long id, Long userId, Long createdItemId, CreatedItemType createdItemType) {
        this.id = id;
        this.userId = userId;
        this.createdItemId = createdItemId;
        this.createdItemType = createdItemType;
    }

    public UserCreationContentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreatedItemId() {
        return createdItemId;
    }

    public void setCreatedItemId(Long createdItemId) {
        this.createdItemId = createdItemId;
    }

    public CreatedItemType getCreatedItemType() {
        return createdItemType;
    }

    public void setCreatedItemType(CreatedItemType createdItemType) {
        this.createdItemType = createdItemType;
    }
}
