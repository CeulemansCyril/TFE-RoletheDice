package com.example.APIRollTheDice.APIRollTheDice_Backend.Model.Obj.Game.Books;

import jakarta.persistence.*;
/** Markdown **/
@Entity
@Table(name = "pages")
public class Pages {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String content;
    private int pageNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "books_id")
    private Book book;



    public Pages() {
    }

    public Pages(Long id, String content, int pageNumber, Book book) {
        this.id = id;
        this.content = content;
        this.pageNumber = pageNumber;
        this.book = book;

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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }


}
