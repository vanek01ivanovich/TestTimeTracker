package com.example.demo.service.facade.impl;

import com.example.demo.data.entity.Project;
import com.example.demo.service.ProjectService;
import com.example.demo.service.TaskService;
import com.example.demo.service.facade.ProjectFacade;
import com.example.demo.web.dto.ProjectRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProjectFacadeImpl implements ProjectFacade {

    private final ProjectService projectService;
    private final TaskService taskService;

    @Autowired
    public ProjectFacadeImpl(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    @Transactional
    public void deleteProject(String id) {
        Project projectById = projectService.getProjectById(id);
        taskService.deleteAllTasksByProject(projectById);
        projectService.deleteProject(projectById);
    }

    @Override
    public void updateProject(String id, ProjectRequestDto projectRequest) {
        Project project = projectRequest.toProjectEntity();
        project.setId(UUID.fromString(id));
        projectService.updateProject(project);
    }
}
