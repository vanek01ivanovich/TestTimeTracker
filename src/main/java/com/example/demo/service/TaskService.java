package com.example.demo.service;

import com.example.demo.data.entity.Task;
import com.example.demo.data.entity.TaskType;
import com.example.demo.web.dto.TaskActivityDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    Task createTask(Task task);

    Task findTaskById(String id);

    void deleteTask(Task task);

    void deleteTaskById(String id);

    void deleteAllTAsks();

    void deleteAllTAsksByStatus(String status);

    void updateTask(Task task);

    void updateStatus(String id, String status);

    List<Task> getAllTasks();

    void deleteAllTasksByProject(String project);

    void assignTaskToUser(String taskId, String userId);

    void deleteUserFromTask(String taskId);

    List<TaskActivityDto> findAllByStatusAndUser(String status, String userId);

    List<TaskActivityDto> findAllByStatus(String status);

    List<Task> findAllByUser(String userId);

    void updateTaskType(String taskId, TaskType taskType);

}
