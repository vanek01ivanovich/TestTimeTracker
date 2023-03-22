package com.example.demo.persistence.repository;

import com.example.demo.data.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface TaskTypeRepository extends JpaRepository<TaskType, UUID> {

    Optional<TaskType> findByName(String name);
}
