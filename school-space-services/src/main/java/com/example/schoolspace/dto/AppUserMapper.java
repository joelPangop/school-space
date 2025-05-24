package com.example.schoolspace.dto;

import com.example.schoolspace.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper implements IMapper<AuthRequest, AppUser>{
    @Override
    public AuthRequest toDto(AppUser entity) {
        if (entity == null) {
            return null;
        }
        AuthRequest dto = new AuthRequest();
        dto.setId(entity.getId());
        dto.setFirstname(entity.getFirstname());
        dto.setLastname(entity.getLastname());
        dto.setEmail(entity.getEmail());
        dto.setRole(entity.getRole());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    @Override
    public AppUser toEntity(AuthRequest dto) {
        if (dto == null) {
            return null;
        }
        AppUser entity = new AppUser();
        entity.setId(dto.getId());
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}
