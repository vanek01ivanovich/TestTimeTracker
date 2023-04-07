package com.example.demo.web.dto;

import com.example.demo.data.entity.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TaskCreateRequestDto {

    private String name;

    private String userId;

    private String projectId;

    private String taskTypeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdWhen;

    public Task toTaskEntity() {
        return Task.builder()
                .name(name)
                .createdWhen(createdWhen)
                .build();
    }

}
