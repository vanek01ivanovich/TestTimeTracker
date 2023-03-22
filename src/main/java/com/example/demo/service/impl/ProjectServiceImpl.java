package com.example.demo.service.impl;

import com.example.demo.data.entity.Project;
import com.example.demo.persistence.repository.ProjectRepository;
import com.example.demo.service.ProjectService;
import com.example.demo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    /*private final TaskService taskService;*/

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository/*, TaskService taskService*/) {
        this.projectRepository = projectRepository;
        /*this.taskService = taskService;*/
    }

    @Override
    public void createProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    @Transactional
    public void deleteProject(Project project) {
        /*taskService.deleteAllTasksByProject(project);*/
        projectRepository.delete(project);
    }

    @Override
    @Transactional
    public void deleteProjectById(String id) {
        Project projectToDelete = projectRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Project is not found with id " + id));
        /*taskService.deleteAllTasksByProject(projectToDelete);*/
        projectRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public void updateProject(Project project) {

    }

    @Override
    public List<Project> getAllProjects() {
        return null;
    }

    @Override
    public Project getProjectById(String id) {
        return null;
    }
}
