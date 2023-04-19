package com.example.demo.service.impl;

import com.example.demo.data.entity.Project;
import com.example.demo.data.entity.Task;
import com.example.demo.data.entity.TaskType;
import com.example.demo.data.entity.User;
import com.example.demo.data.enums.Status;
import com.example.demo.persistence.repository.ProjectRepository;
import com.example.demo.persistence.repository.TaskRepository;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.service.TaskService;
import com.example.demo.web.dto.TaskActivityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public Task createTask(Task task) {
        log.info("createTask task {}", task);
        return taskRepository.save(task);
    }

    @Override
    public Task findTaskById(String id) {
        Task task = taskRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Task is not found with ID {}" + id));
        log.info("findTaskById task {}", task);
        return task;
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public void deleteTaskById(String id) {
        taskRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public void deleteAllTAsks() {
        taskRepository.deleteAll();
    }

    @Override
    public void deleteAllTAsksByStatus(String status) {
        taskRepository.deleteAllByStatus(Status.valueOf(status));
    }

    @Override
    @Transactional
    public void updateTask(Task task) {
        Task taskToUpdate = taskRepository.findById(task.getId())
                .orElseThrow(() -> new RuntimeException(""));
        taskToUpdate.setName(task.getName());
    }

    @Override
    public void updateStatus(String id, String status) {
        Task task = taskRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException(""));
        //get taskTracker
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteAllTasksByProject(String projectId) {
        Project project = projectRepository.findById(UUID.fromString(projectId))
                .orElseThrow(() -> new RuntimeException(""));
        taskRepository.deleteAllByProject(project);
    }

    @Override
    @Transactional
    public void assignTaskToUser(String taskId, String userId) {
        Task task = taskRepository.findById(UUID.fromString(taskId))
                .orElseThrow(() -> new RuntimeException("task is not found with id " + taskId));
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("userId is not found with id " + userId));
        task.setUser(user);
        //taskRepository.assignTaskForUser(UUID.fromString(userId), UUID.fromString(taskId));
    }

    @Override
    @Transactional
    public void deleteUserFromTask(String taskId) {
        Task task = taskRepository.findById(UUID.fromString(taskId))
                .orElseThrow(() -> new RuntimeException(""));
        task.setUser(null);
    }

    @Override
    public List<TaskActivityDto> findAllByStatusAndUser(String status, String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("not found user with id " + userId));
        return taskRepository.findAllByUserAndStatus(user, Status.valueOf(status));
    }

    @Override
    public List<TaskActivityDto> findAllByStatus(String status) {
        return taskRepository.findAllByStatus(Status.valueOf(status));
    }

    @Override
    public List<Task> findAllByUser(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException(""));
        return taskRepository.findAllByUser(user);
    }

    @Override
    @Transactional
    public void updateTaskType(String taskId, TaskType taskType) {
        Task task = taskRepository.findById(UUID.fromString(taskId))
                .orElseThrow(() -> new RuntimeException(""));
        task.setTaskType(taskType);
    }
}
