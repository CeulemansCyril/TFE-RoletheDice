package com.example.APIRollTheDice.Interface.User;


import com.example.APIRollTheDice.Enum.CreatedItemType;
import com.example.APIRollTheDice.Model.Obj.User.UserCreationContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCreationContentInterface extends JpaRepository<UserCreationContent, Long> {
    List<UserCreationContent> findAllByUser_Id(Long userId);

    void deleteByCreatedItemIdAndCreatedItemType(Long createdItemId, CreatedItemType createdItemType);
}
