package com.example.schoolspace.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "students") // optionnel mais conseillé
@NoArgsConstructor
@AllArgsConstructor
public class Student extends AbstractEntity {

    @JsonProperty("_name")
    private String name;

    @JsonProperty("_age")
    private int age;

    @JsonProperty("_email")
    private String email;

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
