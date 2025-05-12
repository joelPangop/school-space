package com.example.studentspace.service;

import com.example.studentspace.dto.CoursDto;
import com.example.studentspace.model.Course;
import com.example.studentspace.model.Student;
import com.example.studentspace.model.Teacher;
import com.example.studentspace.repository.CourseRepository;
import com.example.studentspace.repository.StudentRepository;
import com.example.studentspace.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseServices {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<CoursDto> getAllCours() {
        List<Course> courses = courseRepository.findAll();
        List<CoursDto> coursDtos = new ArrayList<>();
        for (Course course : courses) {
            CoursDto coursDto = getCourseDto(course);
            coursDtos.add(coursDto);
        }
        return coursDtos;
    }

    public CoursDto getCoursTeacherById(int id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(this::getCourseDto).orElse(null);
    }

    @Transactional
    public Course save(CoursDto course) {
        Course newCourse = getCourse(course);
//        Course newCourse = new Course();
//        newCourse.setName(course.getName());
//        newCourse.setSigle(course.getSigle());
//        if (course.getStudents() != null && !course.getStudents().isEmpty()) {
//
//            // Charger les entités Student depuis la base
//            List<Integer> studentIds = course.getStudents();
//            List<Integer> teacherIds = course.getTeachers();
//            List<Student> studentsFromDb = studentRepository.findAllById(studentIds);
//            List<Teacher> teachersFromDb = teacherRepository.findAllById(teacherIds);
//            newCourse.getStudents().addAll(studentsFromDb);
//            newCourse.getTeachers().addAll(teachersFromDb);
//        }
        return courseRepository.save(newCourse);
    }

    @Transactional
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    @Transactional
    public Course updateCourse(Integer id, CoursDto course) {

        return courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setName(course.getName());
                    existingCourse.setSigle(course.getSigle());
                    if (course.getStudents() != null && !course.getStudents().isEmpty()) {
                        // Vider les anciens
                        existingCourse.getStudents().clear();
                        existingCourse.getTeachers().clear();

                        // Charger les entités Student depuis la base
                        List<Integer> studentIds = course.getStudents();
                        List<Integer> teachersIds = course.getTeachers();
                        List<Student> studentsFromDb = studentRepository.findAllById(studentIds);
                        List<Teacher> teachersFromDb = teacherRepository.findAllById(teachersIds);
                        existingCourse.getStudents().addAll(studentsFromDb);
                        existingCourse.getTeachers().addAll(teachersFromDb);
                    }
                    return courseRepository.save(existingCourse);
                })
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    private CoursDto getCourseDto(Course course) {
        CoursDto courseDto = new CoursDto();
        courseDto.setId(course.getId());
        courseDto.setName(course.getName());
        courseDto.setSigle(course.getSigle());
        courseDto.setStudents(course.getStudents().stream().map(Student::getId).collect(Collectors.toList()));
        courseDto.setTeachers(course.getTeachers().stream().map(Teacher::getId).collect(Collectors.toList()));
        return courseDto;
    }

    private Course getCourse(CoursDto courseDto) {
        Course course = new Course();
        course.setId(courseDto.getId());
        course.setName(courseDto.getName());
        course.setSigle(courseDto.getSigle());
        for(Integer id : courseDto.getStudents()) {
            Optional<Student> student = studentRepository.findById(id);
            student.ifPresent(value -> course.getStudents().add(value));
        }
        for(Integer id : courseDto.getTeachers()) {
            Optional<Teacher> teacher = teacherRepository.findById(id);
            teacher.ifPresent(value -> course.getTeachers().add(value));
        }
        return course;
    }
}
