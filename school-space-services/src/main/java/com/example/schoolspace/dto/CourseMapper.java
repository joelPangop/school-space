package com.example.schoolspace.dto;

import com.example.schoolspace.model.Course;
import com.example.schoolspace.model.Student;
import com.example.schoolspace.model.Teacher;
import com.example.schoolspace.repository.StudentRepository;
import com.example.schoolspace.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CourseMapper implements IMapper<CourseDto, Course>{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public CourseDto toDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setName(course.getName());
        courseDto.setSigle(course.getSigle());
        courseDto.setStudents(course.getStudents().stream().map(Student::getId).collect(Collectors.toList()));
        courseDto.setTeachers(course.getTeachers().stream().map(Teacher::getId).collect(Collectors.toList()));
        return courseDto;
    }

    @Override
    public Course toEntity(CourseDto courseDto) {
        if (courseDto == null) {
            return null;
        }
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
