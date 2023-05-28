package com.example.cesinha.controller;

import com.example.cesinha.domain.model.Student;
import com.example.cesinha.domain.request.StudentRequest;
import com.example.cesinha.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> fetchAllStudents() {
        return ResponseEntity.ok(studentService.fetchAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> fetchStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.fetchStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Student> insertStudent(@RequestBody StudentRequest request) {
        Student createdStudent = studentService.insertStudent(request);

        URI location = URI.create(String.format("/students/%s", createdStudent.getId()));
        return ResponseEntity.created(location).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentRequest request) {
        Student updatedStudent = studentService.updateStudent(id, request);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
