package com.example.schoolspace.controller;

import com.example.schoolspace.dto.CourseDto;
import com.example.schoolspace.service.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController extends AbstractController<CourseDto> {
    @Autowired
    private CourseServices courseServices;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> getAll() {
        return courseServices.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public CourseDto getById(@PathVariable Integer id) {
        return courseServices.getById(id);
    }

    @Override
    @PostMapping
    public CourseDto create(@RequestBody CourseDto course) {
        return courseServices.save(course);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(@PathVariable Integer id, @RequestBody CourseDto course) {
        CourseDto updateCourse = courseServices.update(id, course);
        return ResponseEntity.ok(updateCourse);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        courseServices.delete(id);
    }
}
