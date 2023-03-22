package com.example.demo.service.facade.impl;

import com.example.demo.data.entity.Task;
import com.example.demo.service.ProjectService;
import com.example.demo.service.TaskService;
import com.example.demo.service.TaskTypeService;
import com.example.demo.service.UserService;
import com.example.demo.service.facade.TaskFacade;
import com.example.demo.web.dto.TaskCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskFacadeImpl implements TaskFacade {

    private final TaskTypeService taskTypeService;
    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;

    @Autowired
    public TaskFacadeImpl(TaskTypeService taskTypeService,
                          TaskService taskService,
                          UserService userService,
                          ProjectService projectService) {
        this.taskTypeService = taskTypeService;
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @Override
    @Transactional
    public void createTask(TaskCreateRequestDto taskCreateRequest) {
        Task taskToCreate = Task.builder()
                .name(taskCreateRequest.getName())
                .createdWhen(taskCreateRequest.getCreatedWhen())
                .user(userService.getUserById(taskCreateRequest.getUserId()))
                .project(projectService.getProjectById(taskCreateRequest.getProjectId()))
                .taskType(taskTypeService.findTaskTypeByNameOrCreateNew(taskCreateRequest.getTaskTypeName()))
                .build();
        taskService.createTask(taskToCreate);
    }
}
