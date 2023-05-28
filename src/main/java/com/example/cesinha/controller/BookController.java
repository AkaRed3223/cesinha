package com.example.cesinha.controller;

import com.example.cesinha.domain.model.Book;
import com.example.cesinha.domain.request.BookRequest;
import com.example.cesinha.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController extends CrudController<Book, Long, BookRequest> {

    public BookController(BookService bookService) {
        super(bookService);
    }

    @Override
    protected String getResourceName() {
        return "books";
    }
}
