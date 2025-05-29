package com.example.schoolspace.service;

import com.example.schoolspace.dto.StudentDto;
import com.example.schoolspace.dto.StudentMapper;
import com.example.schoolspace.model.Student;
import com.example.schoolspace.model.Teacher;
import com.example.schoolspace.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServices implements IServices<StudentDto, Student>{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<StudentDto> getAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto studentDto = studentMapper.toDto(student);
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }

    @Override
    public StudentDto getById(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        return studentMapper.toDto(student.get());
    }

    @Override
    @Transactional
    public StudentDto save(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        Student newStudent = studentRepository.save(student);
        System.out.println("Saved student: " + newStudent.getName() + " with email: " + newStudent.getEmail());
        return studentMapper.toDto(newStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public StudentDto update(Integer id, StudentDto studentDto) {
        Student student = studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setName(studentDto.getName());
                    existingStudent.setEmail(studentDto.getEmail());
                    existingStudent.setAge(studentDto.getAge());
                    return studentRepository.save(existingStudent);
                })
                .orElseThrow(() -> new RuntimeException("not found"));
        return studentMapper.toDto(student);
    }
}
