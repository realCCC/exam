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

    public Long getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
