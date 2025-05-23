package com.example.schoolspace.services;

import com.example.schoolspace.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

//    @Test
//    void testRepoLoadsAndPersists() {
//        Teacher t = new Teacher();
//        t.setName("Test");
//        t.setAge(20);
//        t.setEmail("test@test.com");
//
//        teacherRepository.save(t);
//        assertEquals(1, teacherRepository.findAll().size());
//    }
}
