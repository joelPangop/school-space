package com.example.schoolspace.controller;

import com.example.schoolspace.dto.CourseDto;
import com.example.schoolspace.dto.Dto;
import com.example.schoolspace.service.IServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public abstract class AbstractController<T extends Dto> {

    public List<T> getAll(){
        return null;
    };

    public T getById(@PathVariable Integer id){
        return null;
    }

    public T create(@RequestBody T dto) {
        return null;
    };

    public ResponseEntity<T> update(@PathVariable Integer id, @RequestBody T dto){
        return null;
    }

    public void delete(@PathVariable Integer id){};
}
