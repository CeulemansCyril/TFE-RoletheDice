package com.example.APIRollTheDice.Interface.ConversationInterface;

import com.example.APIRollTheDice.Model.Obj.Conversation.ConversationRead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConversationReadInterface extends JpaRepository<ConversationRead,Long> {

    Optional<ConversationRead> findByConversationIdAndUserId(Long conversationId, Long userId);

    void deleteConversationReadByConversation_Id(Long conversationId);
}
