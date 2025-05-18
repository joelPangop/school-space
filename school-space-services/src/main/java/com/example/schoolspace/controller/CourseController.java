package com.example.schoolspace.controller;

import com.example.schoolspace.dto.CourseDto;
import com.example.schoolspace.model.Course;
import com.example.schoolspace.service.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://34.238.242.234:3000")
@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseServices courseServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> getAllCourses() {
        return courseServices.getAll();
    }

    @GetMapping("/{id}")
    public CourseDto getTeacherById(@PathVariable Integer id) {
        return courseServices.getById(id);
    }

    @PostMapping
    public CourseDto create(@RequestBody CourseDto course) {
        return courseServices.save(course);
    }

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
