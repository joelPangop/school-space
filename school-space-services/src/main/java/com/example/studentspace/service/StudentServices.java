package com.example.studentspace.service;

import com.example.studentspace.dto.StudentDto;
import com.example.studentspace.model.Student;
import com.example.studentspace.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServices {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto studentDto = getStudentDto(student);
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }

    public StudentDto getStudent(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return getStudentDto(student.get());
        }
        return null;
    }

    @Transactional
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public Student updateStudent(Integer id, Student student) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setName(student.getName());
                    existingStudent.setEmail(student.getEmail());
                    existingStudent.setAge(student.getAge());
                    return studentRepository.save(existingStudent);
                })
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    private StudentDto getStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setAge(student.getAge());
        studentDto.setEmail(student.getEmail());
        return studentDto;
    }
}
