package com.example.schoolspace.service;

import com.example.schoolspace.dto.Dto;
import com.example.schoolspace.dto.TeacherDto;
import com.example.schoolspace.model.AbstractEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IServices<T extends Dto, U extends AbstractEntity> {
    List<T> getAll();
    T getById(Integer id);
    T save(T dto);
    T update(Integer id, T dto);
    void delete(Integer id);
}
