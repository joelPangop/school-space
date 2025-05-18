package com.example.schoolspace.dto;

import com.example.schoolspace.model.Student;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentMapper implements IMapper<StudentDto, Student> {
    @Override
    public StudentDto toDto(Student entity) {
        if (entity == null) {
            return null;
        }
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    @Override
    public Student toEntity(StudentDto dto) {
        if (dto == null) {
            return null;
        }
        Student entity = new Student();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
