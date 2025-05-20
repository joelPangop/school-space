package com.example.schoolspace.controller;

import com.example.schoolspace.dto.StudentDto;
import com.example.schoolspace.model.Student;
import com.example.schoolspace.service.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentServices studentServices;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<StudentDto> getAllStudents() {
        return studentServices.getAll();
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Integer id) {
        return studentServices.getById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public StudentDto create(@RequestBody StudentDto student) {
        return studentServices.save(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable Integer id, @RequestBody StudentDto student) {
        StudentDto updatedStudent = studentServices.update(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        studentServices.delete(id);
    }
}
