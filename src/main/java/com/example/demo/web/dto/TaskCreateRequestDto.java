package com.example.demo.web.dto;

import com.example.demo.data.entity.Task;
import lombok.Data;

import java.util.Date;

@Data
public class TaskCreateRequestDto {

    private String name;

    private String userId;

    private String projectId;

    private String taskTypeName;

    private Date createdWhen;

    public Task toTaskEntity() {
        return Task.builder()
                .name(name)
                .createdWhen(createdWhen)
                .build();
    }

}
