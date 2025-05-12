package com.example.schoolspace.services;

import com.example.schoolspace.dto.TeacherDto;
import com.example.schoolspace.model.Teacher;
import com.example.schoolspace.repository.TeacherRepository;
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

    @Test
    void testGetAllTeachers() {
        Teacher t1 = new Teacher();
        t1.setId(1);
        t1.setName("Alice");
        t1.setAge(41);
        t1.setEmail("alice@test.com");

        Teacher t2 = new Teacher();
        t2.setId(2);
        t2.setAge(52);
        t2.setName("Bob");
        t2.setEmail("bob@ets.ca");

        when(teacherRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<TeacherDto> result = teacherServices.getAllTeachers();

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("bob@ets.ca", result.get(1).getEmail());
        verify(teacherRepository, times(1)).findAll();
    }

    @Test
    void testSaveTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setName("John Doe");
        teacher.setAge(40);
        teacher.setEmail("john@example.com");

        // Simuler le comportement du repository : retourne le même objet
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        // Appel réel de la méthode à tester
        Teacher result = teacherServices.save(teacher);

        // Vérifications
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());

        // Vérifie que save() a bien été appelé une fois
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    void testUpdateTeacher() {
        int id = 1;

        // L'objet existant dans la "base"
        Teacher existing = new Teacher();
        existing.setId(id);
        existing.setName("Old Name");
        existing.setAge(50);
        existing.setEmail("old@example.com");

        // L'objet envoyé pour mise à jour
        Teacher updates = new Teacher();
        updates.setName("New Name");
        updates.setAge(45);
        updates.setEmail("new@example.com");

        // Ce que le repository doit retourner
        when(teacherRepository.findById(id)).thenReturn(Optional.of(existing));
        when(teacherRepository.save(any(Teacher.class))).thenAnswer(i -> i.getArgument(0));

        // Appel de la méthode réelle
        Teacher result = teacherServices.updateTeacher(id, updates);

        // Vérifications
        assertEquals("New Name", result.getName());
        assertEquals(45, result.getAge());
        assertEquals("new@example.com", result.getEmail());

        verify(teacherRepository, times(1)).findById(id);
        verify(teacherRepository, times(1)).save(existing);
    }

    @Test
    void testDeleteTeacher() {
        int id = 1;
        // Appel de la méthode
        teacherServices.deleteTeacher(id);
        // Vérification que deleteById a été appelé une fois avec le bon id
        verify(teacherRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteTeacherThrowsException() {
        int id = 99;
        doThrow(new RuntimeException("Database error"))
                .when(teacherRepository).deleteById(id);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            teacherServices.deleteTeacher(id);
        });
        assertEquals("Database error", ex.getMessage());
        verify(teacherRepository).deleteById(id);
    }

    @Test
    void testSaveWithSqlInjectionInName() {
        Teacher malicious = new Teacher();
        malicious.setName("Robert'); DROP TABLE teachers; --");
        malicious.setEmail("hack@example.com");
        malicious.setAge(30);
        when(teacherRepository.save(malicious)).thenReturn(malicious);
        Teacher result = teacherServices.save(malicious);
        assertEquals("Robert'); DROP TABLE teachers; --", result.getName());
        verify(teacherRepository).save(malicious);
    }

    @Test
    void testFindByIdWithSqlInjectionLikeInput() {
        String userInput = "1 OR 1=1"; // typique d'une tentative d'injection
        assertThrows(NumberFormatException.class, () -> {
            Integer id = Integer.valueOf(userInput); // simulate what would happen if input wasn't validated
            teacherServices.getTeacherById(id);
        });
    }

}
