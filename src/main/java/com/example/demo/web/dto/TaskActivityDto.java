package com.example.demo.web.dto;

import com.example.demo.data.entity.Project;
import com.example.demo.data.entity.User;
import com.example.demo.data.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TaskActivityDto {

    private UUID id;

    private String name;

    private User user;

    //todo check with project, now exception
    //private Project project;

    private Date startedWhen;

    private Date finishedWhen;

    private Integer duration;

    private Status status;

}
