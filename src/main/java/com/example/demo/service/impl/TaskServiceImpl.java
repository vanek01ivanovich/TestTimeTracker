package com.example.demo.service.impl;

import com.example.demo.data.entity.Project;
import com.example.demo.data.entity.Task;
import com.example.demo.persistence.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task findTaskById(String id) {
        return taskRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Task is not found with ID {}" + id));
    }

    @Override
    public void deleteTask(Task task) {

    }

    @Override
    public void deleteTaskById(String id) {

    }

    @Override
    public void deleteAllTAsks() {

    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public void deleteAllTasksByProject(Project project) {

    }

    @Override
    @Transactional
    public void assignTaskToUser(String userId, String taskId) {
        taskRepository.assignTaskForUser(UUID.fromString(userId), UUID.fromString(taskId));
    }
}
