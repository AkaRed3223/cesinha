package com.example.cesinha;

import com.example.cesinha.domain.model.Book;
import com.example.cesinha.domain.model.Student;
import com.example.cesinha.repository.BookRepository;
import com.example.cesinha.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CesinhaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CesinhaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, BookRepository bookRepository) {
        return args -> {
            studentRepository.save(new Student("Cesar","Sales","cesarsales22@gmail.com",32));
            studentRepository.save(new Student("Mi","Sales","misales22@gmail.com",37));

            bookRepository.save(new Book("The Lord of the Rings", "97888845292613", 5));
            bookRepository.save(new Book("A Song of Ice and Fire", "9788496208490", 3));
        };
    }

}
