package com.example.schoolspace.controller;

import com.example.schoolspace.dto.TeacherDto;
import com.example.schoolspace.service.TeacherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController extends AbstractController<TeacherDto>{

    @Autowired
    private TeacherServices teacherServices;

    @Override
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<TeacherDto> getAll() {
        return teacherServices.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public TeacherDto getById(@PathVariable Integer id) {
        return teacherServices.getById(id);
    }

    @Override
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public TeacherDto create(@RequestBody TeacherDto teacher) {
        return teacherServices.save(teacher);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> update(@PathVariable Integer id, @RequestBody TeacherDto teacher) {
        TeacherDto updateTeacher = teacherServices.update(id, teacher);
        return ResponseEntity.ok(updateTeacher);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        teacherServices.delete(id);
    }
}
