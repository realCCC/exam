package com.example.exam.controller;

import com.example.exam.dto.BookDTO;
import com.example.exam.entity.Book;
import com.example.exam.repository.BookRepository;
import com.example.exam.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/list")
    public String bookList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize,
            Model model) {
        Page<BookDTO> bookPage = bookService.getBooksByPage(page, pageSize);

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());

        return "book/list";
    }
    @GetMapping("/book/search")
    public String searchBooks(
            @RequestParam("category") String category,
            @RequestParam("keyword") String keyword,
            Model model
    ) {
        List<BookDTO> searchResults = bookService.searchBooksByCategoryAndKeyword(category, keyword);
        model.addAttribute("books", searchResults);
        return "book/list";
    }

    @GetMapping("/book/create")
    public String showCreateForm() {
        return "/book/create";
    }

    @PostMapping("/book/create")
    public String createBook(@RequestParam("title") String title, @RequestParam("content") String content) {

        BookDTO bookDTO = new BookDTO(null, title, content); // 새로운 BookDTO 객체 생성
        Book savedBook = bookService.saveBook(bookDTO); // BookService를 통해 글을 저장하고 저장된 Book 객체를 반환받음

        // 저장된 글의 번호로 상세 페이지로 리다이렉트
//        return "redirect:/book/detail/" + savedBook.getNumber();
        return "redirect:/book/list";
    }

    @GetMapping("/book/{id}")
    public String showBookDetails(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);

        model.addAttribute("book", book);

        return "/book/detail";
    }

    @PostMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/book/list";
    }

    @GetMapping("/book/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book/edit";
    }

    @PostMapping("/book/edit/{id}")
    public String editBook(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("content") String content) {
        BookDTO bookDTO = new BookDTO(id, title, content);
        bookService.updateBook(bookDTO);
        return "redirect:/book/" + id;
    }

}
