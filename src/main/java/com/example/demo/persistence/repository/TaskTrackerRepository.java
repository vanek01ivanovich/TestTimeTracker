package com.example.demo.persistence.repository;

import com.example.demo.data.entity.Task;
import com.example.demo.data.entity.TaskTracker;
import com.example.demo.data.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskTrackerRepository extends JpaRepository<TaskTracker, UUID> {

    List<TaskTracker> findAllByStatus(Status status);

    TaskTracker findByTask(Task task);

/*
    @Query("")
    List<TaskTracker> findAllByStatusAndName(Status status, String name);
*/

}
