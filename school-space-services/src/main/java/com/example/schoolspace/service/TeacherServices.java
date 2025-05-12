package com.example.studentspace.service;

import com.example.studentspace.dto.CoursDto;
import com.example.studentspace.dto.TeacherDto;
import com.example.studentspace.model.Course;
import com.example.studentspace.model.Student;
import com.example.studentspace.model.Teacher;
import com.example.studentspace.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServices {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public TeacherDto getTeacherById(int id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher.map(this::getTeacherDto).orElse(null);
    }

    @Transactional
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Transactional
    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }

    @Transactional
    public Teacher updateTeacher(Integer id, Teacher teacher) {
        return teacherRepository.findById(id)
                .map(existingTeacher -> {
                    existingTeacher.setName(teacher.getName());
                    existingTeacher.setEmail(teacher.getEmail());
                    existingTeacher.setAge(teacher.getAge());
                    return teacherRepository.save(existingTeacher);
                })
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    private Teacher getTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDto.getId());
        teacher.setName(teacherDto.getName());
        teacher.setAge(teacherDto.getAge());
        teacher.setEmail(teacherDto.getEmail());
        return teacher;
    }

    private TeacherDto getTeacherDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setName(teacher.getName());
        teacherDto.setAge(teacher.getAge());
        teacherDto.setEmail(teacher.getEmail());
        return teacherDto;
    }
}
