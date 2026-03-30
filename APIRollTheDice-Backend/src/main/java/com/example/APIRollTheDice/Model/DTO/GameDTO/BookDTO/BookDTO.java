package com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO;

import com.example.APIRollTheDice.Enum.BookTypes;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public class BookDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Type")
    private BookTypes type;
    @JsonProperty("IdChapters")
    private List<Long> idChapters;
    @JsonProperty("IdGame")
    private Long idGame;
    @JsonProperty("IdGameBundle")
    private Long idGameBundle;

    public BookDTO() {
    }

    public BookDTO(Long id, BookTypes type, String title, List<Long> idChapters, Long idGame, Long idGameBundle) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.idChapters = idChapters;
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

    public List<Long> getIdChapters() {
        return idChapters;
    }

    public void setIdChapters(List<Long> idChapters) {
        this.idChapters = idChapters;
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
