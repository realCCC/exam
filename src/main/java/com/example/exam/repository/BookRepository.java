package com.example.exam.repository;

import com.example.exam.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, QuerydslPredicateExecutor<Book> {
    List<Book> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword);
    Book findById(long id);

    List<Book> findByTitleContaining(String titleKeyword);
    List<Book> findByContentContaining(String contentKeyword);
    List<Book> findByNumberContaining(String numberKeyword);
}
