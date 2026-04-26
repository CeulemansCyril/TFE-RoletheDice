package com.example.APIRollTheDice.Interface;

import com.example.APIRollTheDice.Model.Obj.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageInterface extends JpaRepository<Message, Long> {

    Message findById(long id);
    List<Message> findAllByConversationId(long conversationId);

    boolean existsById(long id);

    void deleteById(long id);


    @Query("""
SELECT COUNT(m)
FROM Message m
WHERE m.conversation.id = :conversationId
AND m.id > :lastReadMessageId
AND m.sender.id != :userId
""")
    int countUnread(Long conversationId, Long lastReadMessageId, Long userId);

}
