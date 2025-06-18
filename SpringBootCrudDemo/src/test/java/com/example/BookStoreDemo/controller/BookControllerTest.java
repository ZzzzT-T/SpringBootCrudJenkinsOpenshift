package com.example.BookStoreDemo.controller;

import com.example.BookStoreDemo.model.Book;
import com.example.BookStoreDemo.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book book1;
    private Book book2;

    @BeforeEach
    public void setup() {
        book1 = new Book(1, "Book One", "Author A", 1999);
        book2 = new Book(2, "Book Two", "Author B", 2005);
    }


    @Test
    public void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/books"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].title").value("Book One"));
    }

    @Test
    public void testGetBookById_Found() throws Exception {
        when(bookService.getBookById(1)).thenReturn(Optional.of(book1));

        mockMvc.perform(get("/api/books/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Book One"));
    }

    @Test
    public void testGetBookById_NotFound() throws Exception {
        when(bookService.getBookById(100)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/100"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateBook() throws Exception {
        when(bookService.createBook(any(Book.class))).thenReturn(book1);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(book1)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Book One"));
    }

    @Test
    public void testUpdateBook_Success() throws Exception {
        when(bookService.updateBook(eq(1), any(Book.class))).thenReturn(book1);

        mockMvc.perform(put("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(book1)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("Book One"));
    }

    @Test
    public void testUpdateBook_NotFound() throws Exception {
        when(bookService.updateBook(eq(100), any(Book.class))).thenThrow(new RuntimeException("Not found"));

        mockMvc.perform(put("/api/books/100")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(book1)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1);

        mockMvc.perform(delete("/api/books/1"))
            .andExpect(status().isNoContent());
    }
}
