package com.example.demo.service.impl;

import com.example.demo.data.entity.Project;
import com.example.demo.data.entity.Task;
import com.example.demo.persistence.repository.TaskRepository;
import com.example.demo.service.ProjectService;
import com.example.demo.service.TaskService;
import com.example.demo.service.TaskTypeService;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.TaskCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskTypeService taskTypeService;


    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           UserService userService,
                           ProjectService projectService,
                           TaskTypeService taskTypeService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.projectService = projectService;
        this.taskTypeService = taskTypeService;
    }

    @Override
    @Transactional
    public void createTask(TaskCreateDto taskCreateDto) {
        /*Task.builder()
                .name(taskCreateDto.getName())
                .createdWhen(taskCreateDto.getCreatedWhen())
                .user(userService.getUserById(taskCreateDto.getUserId().toString()))
                .project(projectService.getProjectById(taskCreateDto.getProjectId().toString()))
                .taskType()*/

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
