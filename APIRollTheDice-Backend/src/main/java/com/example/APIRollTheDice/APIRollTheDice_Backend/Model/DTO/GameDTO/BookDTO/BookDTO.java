package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.BookDTO;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Enum.BookTypes;
import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.GameDTO;


import java.util.List;

public class BookDTO {
    private Long id;
    private String title;
    private BookTypes type;
    private List<Long> idPages;
    private Long idGame;
    private Long idGameBundle;

    public BookDTO() {
    }

    public BookDTO(Long id, BookTypes type, String title, List<Long> idPages, Long idGame, Long idGameBundle) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.idPages = idPages;
        this.idGame = idGame;
        this.idGameBundle = idGameBundle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookTypes getType() {
        return type;
    }

    public void setType(BookTypes type) {
        this.type = type;
    }

    public List<Long> getIdPages() {
        return idPages;
    }

    public void setIdPages(List<Long> idPages) {
        this.idPages = idPages;
    }

    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    public Long getIdGameBundle() {
        return idGameBundle;
    }

    public void setIdGameBundle(Long idGameBundle) {
        this.idGameBundle = idGameBundle;
    }
}
