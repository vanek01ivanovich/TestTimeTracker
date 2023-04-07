package com.example.demo.service;

import com.example.demo.data.entity.TaskType;

public interface TaskTypeService {

    TaskType createTaskType(String taskType);

    TaskType findTaskTypeByNameOrCreateNew(String name);
}
