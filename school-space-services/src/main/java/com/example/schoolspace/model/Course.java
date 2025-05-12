package com.example.schoolspace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course extends AbstractEntity{

    private String name;

    private String sigle;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "courses_students", // nom de la table d'association
            joinColumns = @JoinColumn(name = "course_id"), // clé étrangère vers Course
            inverseJoinColumns = @JoinColumn(name = "student_id") // clé étrangère vers Student
    )
    private Set<Student> students = new HashSet<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "courses_teachers", // nom de la table d'association
            joinColumns = @JoinColumn(name = "course_id"), // clé étrangère vers Course
            inverseJoinColumns = @JoinColumn(name = "teachers_id") // clé étrangère vers Student
    )
    @JsonProperty("_teachers")
    private Set<Teacher> teachers = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}
