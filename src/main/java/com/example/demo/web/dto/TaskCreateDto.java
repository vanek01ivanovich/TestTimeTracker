package com.example.demo.web.dto;

import com.example.demo.data.entity.Project;
import com.example.demo.data.entity.Task;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class TaskCreateDto {

    private String name;

    private UUID userId;

    private UUID projectId;

    private String taskType;

    private Date createdWhen;

    public Task toTaskEntity(){
        return Task.builder()
                .name(name)
                .createdWhen(createdWhen)
                .build();
    }

}
