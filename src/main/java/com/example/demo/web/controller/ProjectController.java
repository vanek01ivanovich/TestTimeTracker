package com.example.demo.web.controller;

import com.example.demo.data.entity.Project;
import com.example.demo.service.ProjectService;
import com.example.demo.web.dto.ProjectCreateDto;
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

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody ProjectCreateDto project){
        log.info("createProject with params {}", project.toString());
        projectService.createProject(project.toProjectEntity());
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProjectByID(@PathVariable String id, @RequestParam Project project){
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProjectById(@PathVariable String id){
    }
}
