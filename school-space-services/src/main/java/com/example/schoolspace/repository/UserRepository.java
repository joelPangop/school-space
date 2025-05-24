package com.example.schoolspace.repository;

import com.example.schoolspace.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    @Query("SELECT t FROM AppUser t WHERE t.email = :email")
    Optional<AppUser> findByEmail(@Param("email") String email);

}
