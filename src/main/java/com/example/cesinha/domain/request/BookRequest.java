package com.example.cesinha.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRequest {

    private String name;
    private String isbn;
    private Integer quantity;
}
