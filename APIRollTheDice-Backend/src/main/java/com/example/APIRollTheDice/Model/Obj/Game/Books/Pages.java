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
    private String title ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapters_id")
    private Chapter chapter;



    public Pages() {
    }

    public Pages(Long id, String content, int pageNumber, Chapter chapter,String title) {
        this.id = id;
        this.content = content;
        this.pageNumber = pageNumber;
        this.chapter = chapter;
        this.title = title;

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

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
