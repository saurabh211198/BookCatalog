package com.example.BookCatalog.controller;


import com.example.BookCatalog.entity.Book;
import com.example.BookCatalog.repository.BookRepository;
import com.example.BookCatalog.service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class BookController {
    @Autowired
    private BookService bookService;

    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookRepository bookRepository;
    private boolean firstcall = true;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        log.info("Creating book: {}", book);
        return bookService.createBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
     return bookService.getBookById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
            Book updatedBook = bookService.updateBook(id,bookDetails);
            if(updatedBook != null) {
                log.info("Updating book with ID: {}", id);
                return ResponseEntity.ok(updatedBook);
            }else {
                return
                        ResponseEntity.notFound().build();
            }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
        boolean isDeleted = bookService.deleteBook(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBookPartially(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Book updatedBook = bookService.updateBookPartially(id, updates);
        if (updatedBook != null) {

            log.info("Partially Updating book with ID: {}", id);
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}









