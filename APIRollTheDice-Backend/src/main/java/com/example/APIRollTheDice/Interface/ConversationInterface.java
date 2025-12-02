package com.example.APIRollTheDice.Interface;


import com.example.APIRollTheDice.Model.Obj.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationInterface extends JpaRepository<Conversation, Long> {

    Optional<Conversation> findById(Long id);
    List<Conversation> findAllByParticipantsId(Long participantId);

    boolean existsById(Long id);

}
