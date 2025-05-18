package com.example.schoolspace.service;

import com.example.schoolspace.dto.CourseDto;
import com.example.schoolspace.dto.CourseMapper;
import com.example.schoolspace.model.Course;
import com.example.schoolspace.model.Student;
import com.example.schoolspace.model.Teacher;
import com.example.schoolspace.repository.CourseRepository;
import com.example.schoolspace.repository.StudentRepository;
import com.example.schoolspace.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseServices implements IServices<CourseDto, Course>{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseDto> getAll() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : courses) {
            CourseDto courseDto = courseMapper.toDto(course);
            courseDtos.add(courseDto);
        }
        return courseDtos;
    }

    @Override
    public CourseDto getById(Integer id) {
        Optional<Course> course = courseRepository.findById(id);
        assert course.orElse(null) != null;
        return courseMapper.toDto(course.orElse(null));
    }

    @Override
    @Transactional
    public CourseDto save(CourseDto course) {
        Course newCourse = courseMapper.toEntity(course);
        Course savedCourse = courseRepository.save(newCourse);
        return courseMapper.toDto(savedCourse);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CourseDto update(Integer id, CourseDto courseDto) {

        Course course = courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setName(courseDto.getName());
                    existingCourse.setSigle(courseDto.getSigle());
                    if (courseDto.getStudents() != null && !courseDto.getStudents().isEmpty()) {
                        // Vider les anciens
                        existingCourse.getStudents().clear();
                        existingCourse.getTeachers().clear();

                        // Charger les entités Student depuis la base
                        List<Integer> studentIds = courseDto.getStudents();
                        List<Integer> teachersIds = courseDto.getTeachers();
                        List<Student> studentsFromDb = studentRepository.findAllById(studentIds);
                        List<Teacher> teachersFromDb = teacherRepository.findAllById(teachersIds);
                        existingCourse.getStudents().addAll(studentsFromDb);
                        existingCourse.getTeachers().addAll(teachersFromDb);
                    }
                    return courseRepository.save(existingCourse);
                })
                .orElseThrow(() -> new RuntimeException("not found"));

        return courseMapper.toDto(course);
    }
}
