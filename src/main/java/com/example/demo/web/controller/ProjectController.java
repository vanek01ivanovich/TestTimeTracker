package com.example.demo.web.controller;

import com.example.demo.data.entity.Project;
import com.example.demo.service.ProjectService;
import com.example.demo.service.facade.ProjectFacade;
import com.example.demo.web.dto.ProjectRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectFacade projectFacade;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectFacade projectFacade) {
        this.projectService = projectService;
        this.projectFacade = projectFacade;
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id) {
        Project projectById = projectService.getProjectById(id);
        return new ResponseEntity<>(projectById, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody ProjectRequestDto project) {
        log.info("createProject with params {}", project.toString());
        projectService.createProject(project.toProjectEntity());
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProjectByID(@PathVariable String id, @RequestBody ProjectRequestDto projectRequest) {
        projectFacade.updateProject(id, projectRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProjectById(@PathVariable String id) {
        projectFacade.deleteProject(id);
    }

}
