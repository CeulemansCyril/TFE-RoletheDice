package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.User;


import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.User.UserCreationContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCreationContentInterface extends JpaRepository<UserCreationContent, Long> {
    List<UserCreationContent> findAllByUser_Id(Long userId);
}
