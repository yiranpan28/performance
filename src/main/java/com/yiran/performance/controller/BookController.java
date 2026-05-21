package com.yiran.performance.controller;

import com.yiran.performance.model.Book;
import com.yiran.performance.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 首页
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Book Library System is running!");
        return "index";
    }

    // 展示所有书籍列表
    @GetMapping("/list")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("newBook", new Book());
        return "bookList";
    }

    // 新增书籍
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/book/list";
    }

    // 删除书籍
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/book/list";
    }
}