package com.example.schoolspace.dto;

import com.example.schoolspace.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TeacherMapper implements IMapper<TeacherDto, Teacher>{
    // This class is responsible for converting between Teacher and TeacherDto

    @Override
    public TeacherDto toDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setAge(teacher.getAge());
        dto.setEmail(teacher.getEmail());
        return dto;
    }

    @Override
    public Teacher toEntity(TeacherDto dto) {
        Teacher teacher = new Teacher();
        teacher.setId(dto.getId());
        teacher.setName(dto.getName());
        teacher.setAge(dto.getAge());
        teacher.setEmail(dto.getEmail());
        return teacher;
    }
}
