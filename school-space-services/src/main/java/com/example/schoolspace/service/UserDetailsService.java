package com.example.schoolspace.service;

import com.example.schoolspace.dto.AuthRequest;
import com.example.schoolspace.dto.AppUserMapper;
import com.example.schoolspace.model.AppUser;
import com.example.schoolspace.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService, IServices<AuthRequest, AppUser> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppUserMapper appUserMapper;

    @Override
    public UserDetails loadUserByUsername(String email) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }

    public List<AuthRequest> getAll() {
        List<AppUser> appUsers = userRepository.findAll();
        List<AuthRequest> authRequests = new ArrayList<>();
        appUsers.forEach(teacher -> authRequests.add(appUserMapper.toDto(teacher)));
        return authRequests;
    }

    public AuthRequest getById(Integer id) {
        Optional<AppUser> appUser = userRepository.findById(id);
        assert appUser.orElse(null) != null;
        return appUserMapper.toDto(appUser.orElse(null));
    }

    @Transactional
    public AuthRequest save(AuthRequest authRequest) {
        AppUser newAppUser = appUserMapper.toEntity(authRequest);
        AppUser savedAppUser = userRepository.save(newAppUser);
        return appUserMapper.toDto(savedAppUser);
    }

    @Transactional
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public AuthRequest update(Integer id, AuthRequest authRequest) {
        AppUser appUser = userRepository.findById(id)
                .map(existingAppUser -> {
                    existingAppUser.setPassword(authRequest.getPassword());
                    existingAppUser.setEmail(authRequest.getEmail());
                    existingAppUser.setRole(authRequest.getRole());
                    return userRepository.save(existingAppUser);
                })
                .orElseThrow(() -> new RuntimeException("not found"));
        return appUserMapper.toDto(appUser);
    }
}
