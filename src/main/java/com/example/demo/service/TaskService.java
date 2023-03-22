package com.example.demo.service;

import com.example.demo.data.entity.Project;
import com.example.demo.data.entity.Task;
import com.example.demo.web.dto.TaskCreateRequestDto;

import java.util.List;

public interface TaskService {

    void createTask(Task task);

    void deleteTask(Task task);

    void deleteTaskById(String id);

    void deleteAllTAsks();

    void updateTask(Task task);

    List<Task> getAllTasks();

    void deleteAllTasksByProject(Project project);
}
