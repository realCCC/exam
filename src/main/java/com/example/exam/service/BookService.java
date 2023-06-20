package com.example.exam.service;


import com.example.exam.dto.BookDTO;
import com.example.exam.entity.Book;
import com.example.exam.repository.BookRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BookDTO convertToDto(Book book) {
        return new BookDTO(book.getNumber(), book.getTitle(), book.getContent());
    }

    public List<Book> searchBooksByKeyword(String keyword) {
        return bookRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }
}