package com.example.demo.persistence.repository;

import com.example.demo.data.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
}
