package com.yiran.performance.service;

import com.yiran.performance.model.Book;
import com.yiran.performance.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 查询所有书籍
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 根据ID查询书籍
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // 新增/更新书籍
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // 删除书籍
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}