package com.example.cesinha.service;

import com.example.cesinha.constants.ResourcesEnum;
import com.example.cesinha.constants.ResourcesFieldsEnum;
import com.example.cesinha.domain.model.Book;
import com.example.cesinha.domain.request.BookRequest;
import com.example.cesinha.exception.ResourceAlreadyExistsException;
import com.example.cesinha.exception.ResourceNotFoundException;
import com.example.cesinha.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService implements CrudService<Book, Long, BookRequest> {

    private final BookRepository bookRepository;

    public List<Book> fetchAll() {
        return bookRepository.findAll();
    }

    public Book fetchById(Long id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourcesEnum.BOOK, id));
    }

    public Book insert(BookRequest request) {
        Book book = new Book(
                request.getName(),
                request.getIsbn(),
                request.getQuantity()
        );

        try {
            bookRepository.save(book);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExistsException(ResourcesEnum.BOOK, ResourcesFieldsEnum.ISBN, request.getIsbn());
        }

        return book;
    }

    public Book update(Long id, BookRequest request) {
        Book book = fetchById(id);

        Optional.ofNullable(request.getName()).ifPresent(book::setName);
        Optional.ofNullable(request.getIsbn()).ifPresent(book::setIsbn);
        Optional.ofNullable(request.getQuantity()).ifPresent(book::setQuantity);

        bookRepository.save(book);

        return book;
    }

    public void delete(Long id) {
        fetchById(id);
        bookRepository.deleteById(id);
    }

    @Override
    public Long getEntityId(Book entity) {
        return entity.getId();
    }
}