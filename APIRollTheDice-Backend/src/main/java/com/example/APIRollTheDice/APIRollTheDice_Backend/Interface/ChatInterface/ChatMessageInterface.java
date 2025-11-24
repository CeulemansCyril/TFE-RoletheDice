package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.ChatInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageInterface extends JpaRepository<ChatMessage,Long> {
    Optional<ChatMessage> findById(Long id);
    List<ChatMessage> findAllByChatChanel(ChatChanel chatChanel);
}
