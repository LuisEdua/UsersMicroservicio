package com.example.usuarios.persistances.repositories;

import com.example.usuarios.persistances.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);
}
