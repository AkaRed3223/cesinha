package com.example.cesinha.service;

import com.example.cesinha.constants.ResourcesEnum;
import com.example.cesinha.domain.model.Student;
import com.example.cesinha.domain.request.StudentRequest;
import com.example.cesinha.exception.ResourceNotFoundException;
import com.example.cesinha.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> fetchAllStudents() {
        return studentRepository.findAll();
    }

    public Student fetchStudentById(Long id) {
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourcesEnum.STUDENT, id));
    }

    public Student insertStudent(StudentRequest request) {
        Student student = new Student(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getAge()
        );

        studentRepository.save(student);
        return student;
    }

    public Student updateStudent(Long id, StudentRequest request) {
        Student student = fetchStudentById(id);

        Optional.ofNullable(request.getFirstName()).ifPresent(student::setFirstName);
        Optional.ofNullable(request.getLastName()).ifPresent(student::setLastName);
        Optional.ofNullable(request.getEmail()).ifPresent(student::setEmail);
        Optional.ofNullable(request.getAge()).ifPresent(student::setAge);

        studentRepository.save(student);

        return student;
    }

    public void deleteStudent(Long id) {
        fetchStudentById(id);
        studentRepository.deleteById(id);
    }
}