package com.example.BookStoreDemo.services;

import com.example.BookStoreDemo.model.Book;
import com.example.BookStoreDemo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(int id, Book bookDetails) {
        return bookRepository.findById(id)
            .map(book -> {
                book.setTitle(bookDetails.getTitle());
                book.setAuthor(bookDetails.getAuthor());
                book.setYear_published(bookDetails.getYear_published());
                return bookRepository.save(book);
            }).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}