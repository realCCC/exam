package com.example.exam.controller;

import com.example.exam.dto.BookDTO;
import com.example.exam.entity.Book;
import com.example.exam.repository.BookRepository;
import com.example.exam.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/list")
    public String bookList(Model model) {
        List<BookDTO> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }
    @GetMapping("/book/search")
    public String searchBooks(@RequestParam("keyword") String keyword, Model model) {
        List<Book> searchResults = bookService.searchBooksByKeyword(keyword);
        model.addAttribute("books", searchResults);
        return "book/list";
    }

}
