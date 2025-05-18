package com.example.schoolspace.service;

import com.example.schoolspace.dto.TeacherDto;
import com.example.schoolspace.dto.TeacherMapper;
import com.example.schoolspace.model.Teacher;
import com.example.schoolspace.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServices implements IServices<TeacherDto, Teacher>{

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;


    public List<TeacherDto> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDto> teacherDtos = new ArrayList<>();
        teachers.forEach(teacher -> teacherDtos.add(teacherMapper.toDto(teacher)));
        return teacherDtos;
    }

    public TeacherDto getById(Integer id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        assert teacher.orElse(null) != null;
        return teacherMapper.toDto(teacher.orElse(null));
    }

    @Transactional
    public TeacherDto save(TeacherDto teacher) {
        Teacher newTeacher = teacherMapper.toEntity(teacher);
        Teacher savedTeacher = teacherRepository.save(newTeacher);
        return teacherMapper.toDto(savedTeacher);
    }

    @Transactional
    public void delete(Integer id) {
        teacherRepository.deleteById(id);
    }

    @Transactional
    public TeacherDto update(Integer id, TeacherDto teacherDto) {
        Teacher teacher = teacherRepository.findById(id)
                .map(existingTeacher -> {
                    existingTeacher.setName(teacherDto.getName());
                    existingTeacher.setEmail(teacherDto.getEmail());
                    existingTeacher.setAge(teacherDto.getAge());
                    return teacherRepository.save(existingTeacher);
                })
                .orElseThrow(() -> new RuntimeException("not found"));
        return teacherMapper.toDto(teacher);
    }

}
