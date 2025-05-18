package com.example.schoolspace.controller;

import com.example.schoolspace.dto.TeacherDto;
import com.example.schoolspace.model.Teacher;
import com.example.schoolspace.service.TeacherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://34.238.242.234:3000")
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherServices teacherServices;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<TeacherDto> getAllTeachers() {
        return teacherServices.getAll();
    }

    @GetMapping("/{id}")
    public TeacherDto getTeacherById(@PathVariable Integer id) {
        return teacherServices.getById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public TeacherDto create(@RequestBody TeacherDto teacher) {
        return teacherServices.save(teacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> update(@PathVariable Integer id, @RequestBody TeacherDto teacher) {
        TeacherDto updateTeacher = teacherServices.update(id, teacher);
        return ResponseEntity.ok(updateTeacher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        teacherServices.delete(id);
    }
}
