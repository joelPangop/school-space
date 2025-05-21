package com.example.schoolspace.dto;

import com.example.schoolspace.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper implements IMapper<AuthRequest, AppUser>{
    @Override
    public AuthRequest toDto(AppUser entity) {
        return null;
    }

    @Override
    public AppUser toEntity(AuthRequest dto) {
        return null;
    }
}
