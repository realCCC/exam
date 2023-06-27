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

//    변수에 접근하고 값을 가져오거나 설정하는 데 사용
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
