package com.example.APIRollTheDice.Model.DTO.Chat;


import java.util.List;
import java.util.Set;

public class ChatChanelDTO {
    private Long id;

    private String name;

    private List<Long> idMessages;

    private Long idGame;



    public ChatChanelDTO() {
    }

    public ChatChanelDTO(Long id, String name, List<Long> idMessages, Long idGame) {
        this.id = id;
        this.name = name;
        this.idMessages = idMessages;
        this.idGame = idGame;
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

    public List<Long> getIdMessages() {
        return idMessages;
    }

    public void setIdMessages(List<Long> idMessages) {
        this.idMessages = idMessages;
    }

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }
}
