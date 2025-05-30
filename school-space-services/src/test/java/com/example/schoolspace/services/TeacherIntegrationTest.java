package com.example.schoolspace.services;

import com.example.schoolspace.StudentSpaceApplication;
import com.example.schoolspace.dto.TeacherDto;
import com.example.schoolspace.repository.TeacherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        properties = "FRONTEND_URL=http://localhost:3000",
        classes = StudentSpaceApplication.class
)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TeacherIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateTeacherIntegration() throws Exception {
        // Nettoyer la BDD avant le test
        teacherRepository.deleteAll();

        TeacherDto teacher = new TeacherDto();
        teacher.setName("Robert'); DROP TABLE teachers; --");
        teacher.setAge(40);
        teacher.setEmail("robert@attack.com");
        // Ne pas setter l'id !

        mockMvc.perform(post("/api/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacher)))
                .andExpect(status().isOk());

        // Vérifie que l'objet a bien été inséré dans la base H2
        var saved = teacherRepository.findAll();
        assertThat(saved).hasSize(1);
        assertThat(saved.get(0).getName()).isEqualTo("Robert'); DROP TABLE teachers; --");
    }

}
