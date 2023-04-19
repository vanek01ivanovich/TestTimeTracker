package com.example.demo.web.controller;

import com.example.demo.service.TaskTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tasks/trackers")
public class TaskTrackerController {

    private final TaskTrackerService taskTrackerService;

    @Autowired
    public TaskTrackerController(TaskTrackerService taskTrackerService) {
        this.taskTrackerService = taskTrackerService;
    }

    @PatchMapping("/start/{id}")
    public void startTask(@PathVariable String id){
        taskTrackerService.startTask(id);
    }

    @PatchMapping("/finish/{id}")
    public void finishTask(@PathVariable String id){
        taskTrackerService.finishTask(id);
    }
}
