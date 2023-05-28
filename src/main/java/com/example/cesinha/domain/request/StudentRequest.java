package com.example.cesinha.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRequest {

    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
}
