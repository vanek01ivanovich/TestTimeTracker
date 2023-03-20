package com.example.demo.service;

import com.example.demo.data.entity.Project;
import com.example.demo.data.entity.Task;
import com.example.demo.web.dto.TaskCreateDto;

import java.util.List;

public interface TaskService {

    void createTask(TaskCreateDto taskCreateDto);

    void deleteTask(Task task);

    void deleteTaskById(String id);

    void deleteAllTAsks();

    void updateTask(Task task);

    List<Task> getAllTasks();

    void deleteAllTasksByProject(Project project);
}
