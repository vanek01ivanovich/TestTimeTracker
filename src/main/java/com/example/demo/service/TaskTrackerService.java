package com.example.demo.service;

import com.example.demo.data.entity.Task;
import com.example.demo.data.entity.TaskTracker;
import com.example.demo.data.enums.Status;
import com.example.demo.web.dto.TaskTrackerDto;

public interface TaskTrackerService {

    void startTask(String id);

    void finishTask(String id);

    void createTaskTracker(Task createdTask);

    void updateTaskTracker(String id, TaskTrackerDto taskTrackerDto);

    void updateStatus(String id, String status);

}
