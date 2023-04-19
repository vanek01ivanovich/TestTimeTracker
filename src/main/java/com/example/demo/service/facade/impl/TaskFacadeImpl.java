package com.example.demo.service.facade.impl;

import com.example.demo.data.entity.Task;
import com.example.demo.service.*;
import com.example.demo.service.facade.TaskFacade;
import com.example.demo.web.dto.TaskCreateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskFacadeImpl implements TaskFacade {

    private final TaskTypeService taskTypeService;
    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskTrackerService taskTrackerService;

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
        log.info("createTask with taskToCreate {}", taskToCreate);
        Task createdTask = taskService.createTask(taskToCreate);
        log.info("created task {}", createdTask);
        taskTrackerService.createTaskTracker(createdTask);
    }
}
