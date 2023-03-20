package com.example.demo.service;

import com.example.demo.data.entity.Project;

import java.util.List;

public interface ProjectService {

    void createProject(Project project);

    void deleteProject(Project project);

    void deleteProjectById(String id);

    void updateProject(Project project);

    List<Project> getAllProjects();

    Project getProjectById(String id);
}
