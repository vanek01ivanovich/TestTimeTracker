package com.example.demo.service.impl;

import com.example.demo.data.entity.TaskType;
import com.example.demo.persistence.repository.TaskTypeRepository;
import com.example.demo.service.TaskTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskTypeServiceImpl implements TaskTypeService {

    private final TaskTypeRepository taskTypeRepository;

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

    @Override
    @Transactional
    public TaskType updateTaskType(TaskType taskType) {
        TaskType taskTypeForUpdate = taskTypeRepository.findById(taskType.getId())
                .orElseThrow(() -> new RuntimeException(""));
        taskTypeForUpdate.setName(taskType.getName());
        return taskTypeForUpdate;
    }
}
