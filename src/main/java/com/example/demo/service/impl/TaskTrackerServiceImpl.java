package com.example.demo.service.impl;

import com.example.demo.data.entity.TaskTracker;
import com.example.demo.persistence.repository.TaskTrackerRepository;
import com.example.demo.service.TaskService;
import com.example.demo.service.TaskTrackerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class TaskTrackerServiceImpl implements TaskTrackerService {

    private final TaskTrackerRepository taskTrackerRepository;
    private final TaskService taskService;

    @Autowired
    public TaskTrackerServiceImpl(TaskTrackerRepository taskTrackerRepository, TaskService taskService) {
        this.taskTrackerRepository = taskTrackerRepository;
        this.taskService = taskService;
    }

    @Override
    public void startTask(String id) {
        TaskTracker taskTrackerToCreate = TaskTracker.builder()
                .task(taskService.findTaskById(id))
                .startedWhen(new Date())
                .build();
        log.info("startTask taskTrackerToCreate {}", taskTrackerToCreate);
        taskTrackerRepository.save(taskTrackerToCreate);
    }

    @Override
    public void finishTask(String id) {
    }
}
