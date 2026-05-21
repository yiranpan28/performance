package com.yiran.performance.controller;

import com.yiran.performance.model.Book;
import com.yiran.performance.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    // 书籍列表 + 搜索功能
    @GetMapping("/list")
    public String listBooks(Model model,
                            @RequestParam(required = false) String keyword) {
        List<Book> books;
        if (keyword != null && !keyword.isBlank()) {
            books = bookService.searchBooks(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            books = bookService.getAllBooks();
        }
        model.addAttribute("books", books);
        model.addAttribute("newBook", new Book());
        return "bookList";
    }

    // 添加书籍
    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute Book book,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Fill in valid book info!");
            return "redirect:/book/list";
        }
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("successMessage", "Book added successfully!");
        return "redirect:/book/list";
    }

    // 跳转编辑页面
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookByIdForEdit(id);
        model.addAttribute("book", book);
        return "editBook";
    }

    // 提交更新书籍
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id,
                             @Valid @ModelAttribute Book book,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Update failed, check your input");
            return "redirect:/book/edit/" + id;
        }
        book.setId(id);
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully!");
        return "redirect:/book/list";
    }

    // 删除书籍
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.deleteBook(id);
            redirectAttributes.addFlashAttribute("successMessage", "Book deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Delete failed!");
        }
        return "redirect:/book/list";
    }
}