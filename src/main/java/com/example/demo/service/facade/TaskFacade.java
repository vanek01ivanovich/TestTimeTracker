package com.example.demo.service.facade;

import com.example.demo.web.dto.TaskCreateRequestDto;

public interface TaskFacade {

    void createTask(TaskCreateRequestDto taskCreateRequest);
}
