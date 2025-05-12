package com.example.studentspace.repository;

import com.example.studentspace.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends AbstractRepository<Teacher, Integer> {
}
