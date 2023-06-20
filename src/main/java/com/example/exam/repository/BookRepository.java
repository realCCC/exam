package com.example.exam.repository;

import com.example.exam.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BookRepository extends JpaRepository<Book, Long> , QuerydslPredicateExecutor<Book> {
}
