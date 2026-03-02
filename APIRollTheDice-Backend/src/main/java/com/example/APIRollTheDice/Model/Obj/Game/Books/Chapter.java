package com.example.APIRollTheDice.Model.Obj.Game.Books;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int chapterNumber;

    @OneToMany(mappedBy = "chapter",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pages> pages ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "books_id")
    private Book books;

    public Chapter(Long id, String title, int chapterNumber, List<Pages> pages, Book books) {
        this.id = id;
        this.title = title;
        this.chapterNumber = chapterNumber;
        this.pages = pages;
        this.books = books;
    }

    public Chapter() {
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

    public List<Pages> getPages() {
        return pages;
    }

    public void setPages(List<Pages> pages) {
        this.pages = pages;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public Book getBooks() {
        return books;
    }

    public void setBooks(Book books) {
        this.books = books;
    }
}
