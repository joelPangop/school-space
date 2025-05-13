package com.example.schoolspace.dto;

import com.example.schoolspace.model.AbstractEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface IMapper<T extends Dto, U extends AbstractEntity> {
    T toDto(U entity);
    U toEntity(T dto);
//    T toDtoWithId(U entity);
//    U toEntityWithId(T dto);
//    T toDtoWithIdAndName(U entity);
//    U toEntityWithIdAndName(T dto);
}
