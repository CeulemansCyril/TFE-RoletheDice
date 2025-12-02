package com.example.APIRollTheDice.Interface.ChatInterface;

import com.example.APIRollTheDice.Model.Obj.Chat.ChatChanel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatChanelInterface extends JpaRepository<ChatChanel,Long>  {
    Optional<ChatChanel> findById(Long id);

    List<ChatChanel> findAllByGame_Id(Long id);


}
