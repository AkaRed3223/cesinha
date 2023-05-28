package com.example.cesinha.controller;

import com.example.cesinha.domain.model.Student;
import com.example.cesinha.domain.request.StudentRequest;
import com.example.cesinha.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController extends CrudController<Student, Long, StudentRequest> {

    public StudentController(StudentService studentService) {
        super(studentService);
    }

    @Override
    protected String getResourceName() {
        return "students";
    }
}
