package com.example.demo.persistence.repository;

import com.example.demo.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
