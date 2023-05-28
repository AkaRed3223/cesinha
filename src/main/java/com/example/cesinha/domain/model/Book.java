package com.example.cesinha.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity(name = "Book")
@Table(name = "book", uniqueConstraints = {@UniqueConstraint(name = "book_isbn_unique", columnNames = "isbn")})
public class Book {

    @Id
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    @Column(name = "id", updatable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "isbn", nullable = false, columnDefinition = "TEXT")
    private String isbn;

    @Column(name = "age", nullable = false)
    private Integer quantity;

    public Book(String name, String isbn, Integer quantity) {
        this.name = name;
        this.isbn = isbn;
        this.quantity = quantity;
    }
}

