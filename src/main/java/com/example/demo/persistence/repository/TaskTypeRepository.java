package com.example.demo.persistence.repository;

import com.example.demo.data.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskTypeRepository extends JpaRepository<TaskType, UUID> {
}
