package com.example.schoolspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.schoolspace.model")
@EnableJpaRepositories(basePackages = "com.example.schoolspace.repository")
public class StudentSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentSpaceApplication.class, args);
    }

}
