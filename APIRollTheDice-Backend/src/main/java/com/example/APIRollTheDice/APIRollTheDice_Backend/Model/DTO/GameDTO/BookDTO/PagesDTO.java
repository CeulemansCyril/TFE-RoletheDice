package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.DTO.GameDTO.BookDTO;

import com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books.Book;

public class PagesDTO {
    private Long id;
    private String content;
    private int pageNumber;
    private Long idBook;

    public PagesDTO(Long id, String content, int pageNumber, Long idBook) {
        this.id = id;
        this.content = content;
        this.pageNumber = pageNumber;
        this.idBook = idBook;
    }

    public PagesDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }
}
