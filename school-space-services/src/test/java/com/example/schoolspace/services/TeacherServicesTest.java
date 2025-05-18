package com.example.schoolspace.services;

import com.example.schoolspace.dto.IMapper;
import com.example.schoolspace.dto.TeacherDto;
import com.example.schoolspace.dto.TeacherMapper;
import com.example.schoolspace.model.Teacher;
import com.example.schoolspace.repository.TeacherRepository;
import com.example.schoolspace.service.IServices;
import com.example.schoolspace.service.TeacherServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherServicesTest {
    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherServices teacherServices;

    @Mock
    private TeacherMapper teacherIMapper;

    @Test
    void testSaveTeacher() {
        // Arrange
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(1);
        teacherDto.setName("John Doe");
        teacherDto.setAge(40);
        teacherDto.setEmail("john@example.com");

        Teacher entity = new Teacher();
        entity.setId(1);
        entity.setName("John Doe");
        entity.setAge(40);
        entity.setEmail("john@example.com");

        // Mocks
        when(teacherIMapper.toEntity(teacherDto)).thenReturn(entity);
        when(teacherRepository.save(entity)).thenReturn(entity);
        when(teacherIMapper.toDto(entity)).thenReturn(teacherDto);

        // Act
        TeacherDto result = teacherServices.save(teacherDto);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());

        verify(teacherRepository, times(1)).save(entity);
    }


    @Test
    void testDeleteTeacher() {
        int id = 1;
        // Appel de la méthode
        teacherServices.delete(id);
        // Vérification que deleteById a été appelé une fois avec le bon id
        verify(teacherRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteTeacherThrowsException() {
        int id = 99;
        doThrow(new RuntimeException("Database error"))
                .when(teacherRepository).deleteById(id);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            teacherServices.delete(id);
        });
        assertEquals("Database error", ex.getMessage());
        verify(teacherRepository).deleteById(id);
    }

    @Test
    void testSaveWithSqlInjectionInName() {
        // Arrange
        TeacherDto malicious = new TeacherDto();
        malicious.setName("Robert'); DROP TABLE teachers; --");
        malicious.setEmail("hack@example.com");
        malicious.setAge(30);

        Teacher entity = new Teacher();
        entity.setName(malicious.getName());
        entity.setEmail(malicious.getEmail());
        entity.setAge(malicious.getAge());

        // Mock the mapping and saving
        when(teacherIMapper.toEntity(malicious)).thenReturn(entity);
        when(teacherRepository.save(entity)).thenReturn(entity);
        when(teacherIMapper.toDto(entity)).thenReturn(malicious);

        // Act
        TeacherDto result = teacherServices.save(malicious);

        // Assert
        assertNotNull(result);
        assertEquals("Robert'); DROP TABLE teachers; --", result.getName());

        // Verify that save was called with the correct entity
        verify(teacherRepository).save(entity);
    }

    @Test
    void testFindByIdWithSqlInjectionLikeInput() {
        String userInput = "1 OR 1=1"; // typique d'une tentative d'injection
        assertThrows(NumberFormatException.class, () -> {
            Integer id = Integer.valueOf(userInput); // simulate what would happen if input wasn't validated
            teacherServices.getById(id);
        });
    }

    @Test
    void testUpdateTeacher() {
        // Arrange
        Integer id = 1;
        TeacherDto updateDto = new TeacherDto();
        updateDto.setName("Alice Updated");
        updateDto.setEmail("alice.updated@example.com");
        updateDto.setAge(40);

        Teacher existing = new Teacher();
        existing.setId(id);
        existing.setName("Old Name");
        existing.setEmail("old@example.com");
        existing.setAge(30);

        Teacher updated = new Teacher();
        updated.setId(id);
        updated.setName(updateDto.getName());
        updated.setEmail(updateDto.getEmail());
        updated.setAge(updateDto.getAge());

        when(teacherRepository.findById(id)).thenReturn(Optional.of(existing));
        when(teacherRepository.save(existing)).thenReturn(updated);
        when(teacherIMapper.toDto(updated)).thenReturn(updateDto);

        // Act
        TeacherDto result = teacherServices.update(id, updateDto);

        // Assert
        assertNotNull(result);
        assertEquals("Alice Updated", result.getName());
        verify(teacherRepository).save(existing);
    }

    @Test
    void testGetAllTeachers() {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setName("John");

        TeacherDto dto = new TeacherDto();
        dto.setName("John");

        when(teacherRepository.findAll()).thenReturn(List.of(teacher));
        when(teacherIMapper.toDto(teacher)).thenReturn(dto);

        // Act
        List<TeacherDto> result = teacherServices.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
    }

    @Test
    void testGetById() {
        // Arrange
        Integer id = 1;
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName("John");

        TeacherDto dto = new TeacherDto();
        dto.setName("John");

        when(teacherRepository.findById(id)).thenReturn(Optional.of(teacher));
        when(teacherIMapper.toDto(teacher)).thenReturn(dto);

        // Act
        TeacherDto result = teacherServices.getById(id);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getName());
    }

}
