package edu.vikadem.mypr.controller;

import edu.vikadem.mypr.model.Student;
import edu.vikadem.mypr.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import request.StudentCreateRequest;
import request.StudentUpdateRequest;

import java.util.List;

/*
@author admin
@mypr
@class ItemRestController
@since 19.04.2025 - 13.44

*/
@RestController
@RequestMapping("api/v1/students/")
@RequiredArgsConstructor
public class ItemRestController {
    private final StudentService studentService;

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student showOneById(@PathVariable String id) {
        return studentService.getById(id);
    }

    @PostMapping
    public Student insert(@RequestBody Student student) {
        return studentService.create(student);
    }

    //request
    @PostMapping("/dto")
    public Student insert(@RequestBody StudentCreateRequest request) {return studentService.create(request);}

    @PutMapping
    public Student edit(@RequestBody Student student) {
        return studentService.update(student);
    }

    //request
    @PutMapping("/dto")
    public Student edit(@RequestBody StudentUpdateRequest request) {return studentService.update(request);}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        studentService.delById(id);
    }
}
