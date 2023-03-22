package com.example.demo.service.impl;

import com.example.demo.data.entity.Project;
import com.example.demo.data.entity.Task;
import com.example.demo.persistence.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
