package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageInterface extends JpaRepository<Message, Long> {

    Message findById(long id);
    List<Message> findAllByConversationId(long conversationId);

    boolean existsById(long id);

    void deleteById(long id);
}
