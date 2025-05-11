package com.example.studentspace.repository;

import com.example.studentspace.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends AbstractRepository<Student, Integer> {
}
