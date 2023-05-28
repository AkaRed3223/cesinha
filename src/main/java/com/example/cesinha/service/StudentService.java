package com.example.cesinha.service;

import com.example.cesinha.constants.ResourcesEnum;
import com.example.cesinha.constants.ResourcesFieldsEnum;
import com.example.cesinha.domain.model.Student;
import com.example.cesinha.domain.request.StudentRequest;
import com.example.cesinha.exception.ResourceAlreadyExistsException;
import com.example.cesinha.exception.ResourceNotFoundException;
import com.example.cesinha.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService implements CrudService<Student, Long, StudentRequest> {

    private final StudentRepository studentRepository;

    public List<Student> fetchAll() {
        return studentRepository.findAll();
    }

    public Student fetchById(Long id) {
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourcesEnum.STUDENT, id));
    }

    public Student insert(StudentRequest request) {
        Student student = new Student(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getAge()
        );

        try {
            studentRepository.save(student);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceAlreadyExistsException(ResourcesEnum.STUDENT, ResourcesFieldsEnum.EMAIL, request.getEmail());
        }

        return student;
    }

    public Student update(Long id, StudentRequest request) {
        Student student = fetchById(id);

        Optional.ofNullable(request.getFirstName()).ifPresent(student::setFirstName);
        Optional.ofNullable(request.getLastName()).ifPresent(student::setLastName);
        Optional.ofNullable(request.getEmail()).ifPresent(student::setEmail);
        Optional.ofNullable(request.getAge()).ifPresent(student::setAge);

        studentRepository.save(student);

        return student;
    }

    public void delete(Long id) {
        fetchById(id);
        studentRepository.deleteById(id);
    }

    @Override
    public Long getEntityId(Student entity) {
        return entity.getId();
    }
}