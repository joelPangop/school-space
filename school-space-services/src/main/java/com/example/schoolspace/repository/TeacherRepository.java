package com.example.schoolspace.repository;

import com.example.schoolspace.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends AbstractRepository<Teacher, Integer> {
}
