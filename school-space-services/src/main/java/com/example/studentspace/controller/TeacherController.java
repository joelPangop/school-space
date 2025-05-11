package com.example.studentspace.controller;

import com.example.studentspace.dto.TeacherDto;
import com.example.studentspace.model.Teacher;
import com.example.studentspace.service.TeacherServices;
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
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherServices teacherServices;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Teacher> getAllTeachers() {
        return teacherServices.getAllTeachers();
    }

    @GetMapping("/{id}")
    public TeacherDto getTeacherById(@PathVariable Integer id) {
        return teacherServices.getTeacherById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Teacher create(@RequestBody Teacher teacher) {
        return teacherServices.save(teacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable Integer id, @RequestBody Teacher teacher) {
        Teacher updateTeacher = teacherServices.updateTeacher(id, teacher);
        return ResponseEntity.ok(updateTeacher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        teacherServices.deleteTeacher(id);
    }
}
