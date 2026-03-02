package com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO;

import com.example.APIRollTheDice.Model.Obj.Game.Books.Book;

import java.util.List;

public class ChapterDTO {
    private Long id;
    private String title;
    private int chapterNumber;
    private List<Long> idPages;
    private Long idBook;

    public ChapterDTO(Long id, String title, int chapterNumber, List<Long> idPages, Long idBook) {
        this.id = id;
        this.title = title;
        this.chapterNumber = chapterNumber;
        this.idPages = idPages;
        this.idBook = idBook;
    }

    public ChapterDTO() {
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

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public List<Long> getIdPages() {
        return idPages;
    }

    public void setIdPages(List<Long> idPages) {
        this.idPages = idPages;
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }
}
