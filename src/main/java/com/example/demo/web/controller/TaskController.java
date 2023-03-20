package com.example.demo.web.controller;

import com.example.demo.data.entity.Task;
import com.example.demo.service.TaskService;
import com.example.demo.web.dto.TaskCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskCreateDto taskCreateDto){
        taskService.createTask(taskCreateDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable String id){
    }
}
