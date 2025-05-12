package com.example.schoolspace.repository;

import com.example.schoolspace.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends AbstractRepository<Student, Integer> {
}
