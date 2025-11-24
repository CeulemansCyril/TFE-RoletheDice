package com.example.APIRollTheDice.APIRollTheDice_Backend.Interface.ChatInterface;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat.ChatChanel;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatChanelInterface extends JpaRepository<ChatChanel,Long>  {
    Optional<ChatChanel> findById(Long id);

    List<ChatChanel> findAllByGame_Id(Long id);


}
