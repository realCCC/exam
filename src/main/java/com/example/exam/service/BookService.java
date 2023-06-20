package com.example.exam.service;


import com.example.exam.dto.BookDTO;
import com.example.exam.entity.Book;
import com.example.exam.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> findAllBooks() {  //모든 책 조회
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BookDTO convertToDto(Book book) {
        return new BookDTO(book.getNumber(), book.getTitle(), book.getContent());
    }

    public List<Book> searchBooksByKeyword(String keyword) {  //키워드로 검색
        return bookRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }

    public Page<BookDTO> getBooksByPage(int page, int size){ //JPA의 Pageable을 사용해 페이징 처리 //페이지별로 책 조회
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Book> bookPage = bookRepository.findAll(pageable);
        return bookPage.map(book -> new BookDTO(book.getNumber(), book.getTitle(), book.getContent()));
    }

    public Book saveBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setContent(bookDTO.getContent());

        return bookRepository.save(book); // Book 객체를 저장하고 저장된 Book 객체를 반환
    }

    public Book getBookById(long id) {
        return bookRepository.findById(id);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(BookDTO bookDTO) {
        Book book = bookRepository.findById(bookDTO.getNumber())
                .orElseThrow(() -> new IllegalArgumentException("Invalid book number: " + bookDTO.getNumber()));

        book.setTitle(bookDTO.getTitle());
        book.setContent(bookDTO.getContent());

        return bookRepository.save(book);
    }


}