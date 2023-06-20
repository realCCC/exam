package com.example.exam.dto;

public class BookDTO {
    private Long number;
    private String title;
    private String content;

    public BookDTO() {
    }

    public BookDTO(Long number, String title, String content) {
        this.number = number;
        this.title = title;
        this.content = content;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
