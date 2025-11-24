package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface;


import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationInterface extends JpaRepository<Conversation, Long> {

    Optional<Conversation> findById(Long id);
    List<Conversation> findAllByParticipantsId(Long participantId);

    boolean existsById(Long id);

}
