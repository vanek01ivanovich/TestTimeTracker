package com.example.demo.service;

import com.example.demo.data.entity.Project;
import com.example.demo.data.entity.Task;
import com.example.demo.web.dto.TaskCreateRequestDto;

import java.util.List;

public interface TaskService {

    void createTask(Task task);

    Task findTaskById(String id);

    void deleteTask(Task task);

    void deleteTaskById(String id);

    void deleteAllTAsks();

    void updateTask(Task task);

    List<Task> getAllTasks();

    void deleteAllTasksByProject(Project project);

    void assignTaskToUser(String userId, String taskId);
}
