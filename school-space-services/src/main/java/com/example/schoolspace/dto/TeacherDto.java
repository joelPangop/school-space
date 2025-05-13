package com.example.schoolspace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherDto extends Dto {

    @JsonProperty("_name")
    private String name;

    @JsonProperty("_age")
    private int age;

    @JsonProperty("_email")
    private String email;

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
