package com.example.APIRollTheDice.Model.Obj.Game.Books;

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
    private Book books;



    public Pages() {
    }

    public Pages(Long id, String content, int pageNumber, Book books) {
        this.id = id;
        this.content = content;
        this.pageNumber = pageNumber;
        this.books = books;

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

    public Book getBooks() {
        return books;
    }

    public void setBooks(Book books) {
        this.books = books;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }


}
