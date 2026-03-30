package com.example.APIRollTheDice.Model.DTO.GameDTO.BookDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PagesDTO {
    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Content")
    private String content;
    @JsonProperty("PageNumber")
    private int pageNumber;
    @JsonProperty("IdChapter")
    private Long idChapter;
    @JsonProperty("Title")
    private String title ;

    public PagesDTO(Long id, String content, int pageNumber, Long idChapter, String title) {
        this.id = id;
        this.content = content;
        this.pageNumber = pageNumber;
        this.idChapter = idChapter;
        this.title =title;
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

    public Long getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(Long idChapter) {
        this.idChapter = idChapter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
