package com.yiran.performance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book name cannot be empty")
    private String bookName;

    @NotBlank(message = "Author cannot be empty")
    private String author;

    @Positive(message = "Price must be greater than 0")
    private Double price;

    // 无参构造
    public Book() {}

    // 有参构造
    public Book(String bookName, String author, Double price) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}