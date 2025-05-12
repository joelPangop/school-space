package com.example.schoolspace.controller;

import com.example.schoolspace.dto.CoursDto;
import com.example.schoolspace.model.Course;
import com.example.schoolspace.service.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseServices courseServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CoursDto> getAllCourses() {
        return courseServices.getAllCours();
    }

    @GetMapping("/{id}")
    public CoursDto getTeacherById(@PathVariable Integer id) {
        return courseServices.getCoursTeacherById(id);
    }

    @PostMapping
    public CoursDto create(@RequestBody CoursDto course) {
        return courseServices.save(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Integer id, @RequestBody CoursDto course) {
        Course updateCourse = courseServices.updateCourse(id, course);
        return ResponseEntity.ok(updateCourse);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        courseServices.deleteCourse(id);
    }
}
