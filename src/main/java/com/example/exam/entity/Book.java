package com.example.exam.entity;

import jakarta.persistence.*;

@Entity
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(length = 100,nullable = false)
    private String title;

    @Column(length = 100,nullable = false)
    private String content;
    public Book() {
    }

    public Book(String title, String content) {
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
