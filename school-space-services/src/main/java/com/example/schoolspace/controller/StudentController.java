package com.example.studentspace.controller;

import com.example.studentspace.dto.StudentDto;
import com.example.studentspace.model.Student;
import com.example.studentspace.service.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentServices studentServices;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<StudentDto> getAllStudents() {
        return studentServices.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Integer id) {
        return studentServices.getStudent(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Student create(@RequestBody Student student) {
        return studentServices.save(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Integer id, @RequestBody Student student) {
        Student updatedStudent = studentServices.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        studentServices.deleteStudent(id);
    }
}
