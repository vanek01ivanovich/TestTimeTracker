package com.example.demo.web.controller;

import com.example.demo.data.entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestParam Task task){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable String id){
    }
}
