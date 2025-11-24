package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Chat;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.Chat.ChatChanelDTO;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Game;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chat_chanel")
public class ChatChanel {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "chatChanel", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ChatMessage> messages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;




    public ChatChanel() {
    }

    public ChatChanel(Long id, String name, Game game, List<ChatMessage> messages) {
        this.id = id;
        this.name = name;
        this.game = game;
        this.messages = messages;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public void addMessage(ChatMessage message) {
        this.messages.add(message);
    }

    public void removeMessage(ChatMessage message) {
        this.messages.remove(message);
    }

    public void UpdateMessage(ChatMessage oldMessage, ChatMessage newMessage) {
        if(this.messages.contains(oldMessage)) {
            oldMessage.setMessageContent(newMessage.getMessageContent());
            oldMessage.setModified(true);
            messages.remove(oldMessage);
            messages.add(oldMessage);
        }
    }

}
