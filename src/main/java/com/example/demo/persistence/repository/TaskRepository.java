package com.example.demo.persistence.repository;

import com.example.demo.data.entity.Task;
import com.example.demo.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByUser(User user);

    @Modifying
    @Query(value = "update Task t set t.user.id = :userId where t.id = :taskId")
    void assignTaskForUser(@Param("userId") UUID userId, @Param("taskId") UUID taskId);



}
