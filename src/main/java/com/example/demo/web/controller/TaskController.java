package com.example.demo.web.controller;

import com.example.demo.data.entity.Task;
import com.example.demo.data.entity.TaskType;
import com.example.demo.service.TaskService;
import com.example.demo.service.TaskTypeService;
import com.example.demo.service.facade.TaskFacade;
import com.example.demo.web.dto.TaskActivityDto;
import com.example.demo.web.dto.TaskCreateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RequiredArgsConstructor
public class TaskController {

    private final TaskFacade taskFacade;
    private final TaskService taskService;
    private final TaskTypeService taskTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        log.info("getTaskById with id {}", id);
        Task taskById = taskService.findTaskById(id);
        return new ResponseEntity<>(taskById, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody TaskCreateRequestDto taskCreateDto) {
        log.info("createTask with params {}", taskCreateDto.toString());
        taskFacade.createTask(taskCreateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTaskById(id);
    }

    @DeleteMapping("/status")
    public void deleteAllTasksByStatus(@RequestParam String status) {
        taskService.deleteAllTAsksByStatus(status);
    }

    @DeleteMapping("/all")
    public void deleteAllTasks() {
        taskService.deleteAllTAsks();
    }

    @DeleteMapping("/projects/{id}")
    public void deleteAllTasksByProject(@PathVariable String id) {
        taskService.deleteAllTasksByProject(id);
    }

    @PatchMapping
    public void updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> allTasks = taskService.getAllTasks();
        return new ResponseEntity<>(allTasks,HttpStatus.OK);
    }

 /*   @GetMapping("/all")
    public ResponseEntity<List<TaskActivityDto>> getAllTasksActivity(){
        return null;
    }*/

    @PatchMapping("/status/{id}")
    public void updateTaskStatus(@PathVariable String id, @RequestParam String status) {
        taskService.updateStatus(id, status);
    }

    @PatchMapping("/type/{id}")
    public void updateTaskType(@PathVariable String id, @RequestBody TaskType taskType) {
        TaskType updatedTaskType = taskTypeService.updateTaskType(taskType);
        taskService.updateTaskType(id, updatedTaskType);
    }

    @PatchMapping("/{taskId}/update/user/{userId}")
    public void assignTaskToUser(@PathVariable String taskId, @PathVariable String userId) {
        log.info("assignTaskToUser with params taskId {}, userId {}", taskId, userId);
        taskService.assignTaskToUser(taskId, userId);
    }

    @PatchMapping("/{id}")
    public void deleteUserFromTask(@PathVariable String id){
        taskService.deleteUserFromTask(id);
    }

        @GetMapping("/all/by/status/users")
    public ResponseEntity<List<TaskActivityDto>> getAllByStatusAndUser(@RequestParam String status, @RequestParam String userId){
        List<TaskActivityDto> allByStatusAndUser = taskService.findAllByStatusAndUser(status, userId);
        return new ResponseEntity<>(allByStatusAndUser, HttpStatus.OK);
    }

    @GetMapping("/all/by/status")
    public ResponseEntity<List<TaskActivityDto>> getAllByStatus(@RequestParam String status){
        List<TaskActivityDto> allByStatus = taskService.findAllByStatus(status);
        return new ResponseEntity<>(allByStatus, HttpStatus.OK);
    }

    @GetMapping("/all/by/users")
    public ResponseEntity<List<Task>> getAllByUser(@RequestParam String userId){
        List<Task> allByStatus = taskService.findAllByUser(userId);
        return new ResponseEntity<>(allByStatus, HttpStatus.OK);
    }
}
