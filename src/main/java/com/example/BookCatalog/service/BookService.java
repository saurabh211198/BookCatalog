package com.example.BookCatalog.service;

import com.example.BookCatalog.controller.BookController;
import com.example.BookCatalog.entity.Book;
import com.example.BookCatalog.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    @Cacheable(value = "books", key ="'all'")
    public List<Book> getAllBooks() {
        log.info("CACHE MISS: Fetching all books");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error("Error while fetching all books");
            e.printStackTrace();
        }
        return bookRepository.findAll();
    }

    @Cacheable(value = "bookById", key = "#id")
    public ResponseEntity<Book> getBookById( Long id) {
        log.info("Cache MISS(Spring handle this)-Fetching Book with ID: {}", id);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error("Error while Fetching Book with ID {}:{}", id);
            e.printStackTrace();
        }
        return bookRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @CacheEvict(value = "books",allEntries = true)
    public Book createBook( Book book) {
        log.info("Creating book: {}", book);
        return bookRepository.save(book);
    }
     @Caching(evict = {
        @CacheEvict(value =  "books", allEntries = true), @CacheEvict(value = "bookById", key ="#id")
     })
    public Book updateBook( Long id,  Book bookDetails) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPrice(bookDetails.getPrice());
            Book updatedBook = bookRepository.save(book);
            log.info("Updating book with ID: {}", id);
            return updatedBook;
        }).orElse(null);
    }

    @Caching(evict = {
            @CacheEvict(value =  "books", allEntries = true), @CacheEvict(value = "bookById", key ="#id")
    })
    public Book updateBookPartially(Long id, Map<String, Object> updates) {
        return bookRepository.findById(id).map(book -> {
            if (updates.containsKey("title")) {
                book.setTitle((String) updates.get("title"));
            }
            if (updates.containsKey("author")) {
                book.setAuthor((String) updates.get("author"));
            }
            if (updates.containsKey("price")) {
                book.setPrice(Double.parseDouble(updates.get("price").toString()));
            }
            log.info("Partially Updating book with ID: {}", id);
            return bookRepository.save(book);
        }).orElse(null);
    }

    @Caching(evict = {
            @CacheEvict(value =  "books", allEntries = true), @CacheEvict(value = "bookById", key ="#id")
    })
    public boolean deleteBook( Long id) {
        return bookRepository.findById(id).map(book -> {
            bookRepository.delete(book);
            log.info("Deleting book with ID: {}", id);

            return true;
        }).orElse(false);
    }
}
