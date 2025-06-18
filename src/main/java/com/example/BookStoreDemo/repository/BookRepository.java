package com.example.BookStoreDemo.repository;
import com.example.BookStoreDemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // ...existing code...
}