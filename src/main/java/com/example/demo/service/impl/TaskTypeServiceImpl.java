package com.example.demo.service.impl;

import com.example.demo.data.entity.TaskType;
import com.example.demo.persistence.repository.TaskTypeRepository;
import com.example.demo.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskTypeServiceImpl implements TaskTypeService {

    private final TaskTypeRepository taskTypeRepository;

    @Autowired
    public TaskTypeServiceImpl(TaskTypeRepository taskTypeRepository) {
        this.taskTypeRepository = taskTypeRepository;
    }

    @Override
    public TaskType createTaskType(String taskType) {
        return taskTypeRepository.save(TaskType.builder()
                .name(taskType)
                .build());
    }

    @Override
    public TaskType findTaskTypeByNameOrCreateNew(String name) {
        return taskTypeRepository.findByName(name)
                .orElseGet(() -> createTaskType(name));
    }
}
