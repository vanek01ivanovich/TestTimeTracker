package com.example.demo.service.impl;

import com.example.demo.data.entity.Task;
import com.example.demo.data.entity.TaskTracker;
import com.example.demo.data.enums.Status;
import com.example.demo.persistence.repository.TaskTrackerRepository;
import com.example.demo.service.TaskTrackerService;
import com.example.demo.web.dto.TaskTrackerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskTrackerServiceImpl implements TaskTrackerService {

    private final TaskTrackerRepository taskTrackerRepository;

    @Override
    @Transactional
    public void startTask(String id) {
        TaskTracker taskToStart = taskTrackerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("task with id " + id + " is not found"));
        log.info("startTask taskTrackerToCreate {}", taskToStart);
        taskToStart.setStartedWhen(new Date());
        //todo when start -> change status
    }

    @Override
    @Transactional
    public void finishTask(String id) {
        TaskTracker taskToFinish = taskTrackerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("task with id " + id + " is not found"));
        log.info("finishTask taskTrackerToCreate {}", taskToFinish);
        Date startedWhen = taskToFinish.getStartedWhen();
        Date finishedWhen = new Date();
        long abs = Math.abs(finishedWhen.getTime() - startedWhen.getTime());
        taskToFinish.setFinishedWhen(finishedWhen);
        taskToFinish.setDuration(Integer.valueOf(Long.toString(abs)));
        //todo when finish -> change status
    }

    @Override
    public void createTaskTracker(Task createdTask) {
        log.info("createTaskTracker with task {}", createdTask);
        TaskTracker taskTracker = TaskTracker.builder()
                .status(Status.NOT_STARTED)
                .build();
        createdTask.setTaskTracker(taskTracker);
    }

    @Override
    @Transactional
    public void updateTaskTracker(String id, TaskTrackerDto taskTrackerDto) {
        TaskTracker taskTracker = taskTrackerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException(""));
        taskTracker.setDuration(taskTrackerDto.getDuration());
        taskTracker.setStartedWhen(taskTrackerDto.getStartedWhen());
        taskTracker.setFinishedWhen(taskTrackerDto.getFinishedWhen());
        taskTracker.setStatus(taskTracker.getStatus());
    }

    @Override
    @Transactional
    public void updateStatus(String id, String status) {
        TaskTracker taskTracker = taskTrackerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException(""));
        taskTracker.setStatus(Status.valueOf(status));
    }
}
