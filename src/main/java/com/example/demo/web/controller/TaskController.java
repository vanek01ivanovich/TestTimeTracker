package com.example.demo.web.controller;

import com.example.demo.data.entity.Task;
import com.example.demo.service.TaskService;
import com.example.demo.service.facade.TaskFacade;
import com.example.demo.web.dto.TaskCreateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TaskController {

    private final TaskFacade taskFacade;
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskFacade taskFacade, TaskService taskService) {
        this.taskFacade = taskFacade;
        this.taskService = taskService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody TaskCreateRequestDto taskCreateDto) {
        log.info("createTask with params {}", taskCreateDto.toString());
        taskFacade.createTask(taskCreateDto);
    }

    @DeleteMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable String id) {
    }

    @PatchMapping("/{taskId}/update/user/{userId}")
    public void assignTaskToUser(@PathVariable String taskId, @PathVariable String userId) {
        log.info("assignTaskToUser with params taskId {}, userId {}", taskId, userId);
        taskService.assignTaskToUser(userId, taskId);
    }


}
