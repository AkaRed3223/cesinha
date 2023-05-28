package com.example.cesinha.controller;

import com.example.cesinha.domain.model.Book;
import com.example.cesinha.domain.request.BookRequest;
import com.example.cesinha.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> fetchAllBooks() {
        return ResponseEntity.ok(bookService.fetchAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> fetchStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.fetchBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> insertBook(@RequestBody BookRequest request) {
        Book createdBook = bookService.insertBook(request);

        URI location = URI.create(String.format("/books/%s", createdBook.getId()));
        return ResponseEntity.created(location).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookRequest request) {
        Book updatedBook = bookService.updateBook(id, request);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
