package edu.vikadem.mypr.service;

/*
@author admin
@mypr
@class StudentService
@since 19.04.2025 - 12.53

*/

import edu.vikadem.mypr.model.Student;
import edu.vikadem.mypr.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import request.StudentCreateRequest;
import request.StudentUpdateRequest;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor

public class StudentService {
    private final StudentRepository studentRepository;

    private List<Student> students = new ArrayList<>();
    {
        students.add(new Student("1", "Vika", "0000001", "3 course"));
        students.add(new Student("2", "Lida", "0000002", "2 course"));
        students.add(new Student("3", "Ira", "0000003", "1 course"));
    }
    @PostConstruct
    void init() {
        studentRepository.deleteAll();
        studentRepository.saveAll(students);
    }

    //CRUD

    public List<Student> getAll(){
        return studentRepository.findAll();
    }
    public Student getById(String id) {
        return studentRepository.findById(id).orElse(null);
    }
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student create(StudentCreateRequest request) {
        Student student = mapToStudent(request);
        student.setCreateDate(LocalDateTime.now());
        student.setUpdateDate(new ArrayList<LocalDateTime>());
        return studentRepository.save(student);
    }

    public  Student update(Student student) {
        return studentRepository.save(student);
    }


    public void delById(String id) {
        studentRepository.deleteById(id);
    }

    private Student mapToStudent(StudentCreateRequest request) {
        Student student = new Student(request.name(), request.code(), request.description());
        return student;
    }

    public Student update(StudentUpdateRequest request) {
        Student studentPersisted = studentRepository.findById(request.id()).orElse(null);
        if (studentPersisted != null) {
            List<LocalDateTime> updateDates = studentPersisted.getUpdateDate();
            updateDates.add(LocalDateTime.now());
            Student studentToUpdate =
                    Student.builder()
                            .id(request.id())
                            .name(request.name())
                            .code(request.code())
                            .description(request.description())
                            .createDate(studentPersisted.getCreateDate())
                            .updateDate(updateDates)
                            .build();
            return studentRepository.save(studentToUpdate);

        }
        return null;
    }


}
