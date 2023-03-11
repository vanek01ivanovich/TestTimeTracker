package com.example.demo.web.controller;

import com.example.demo.data.entity.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestParam Project project){
        return new ResponseEntity<>(null, HttpStatus.CREATED);
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
