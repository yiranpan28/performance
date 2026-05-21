package com.yiran.performance.repository;

import com.yiran.performance.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // 按书名或作者模糊搜索
    List<Book> findByBookNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(String name, String author);
}