package com.example.demo.web.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaskTrackerDto {

    private String name;

    private Date startedWhen;

    private Date finishedWhen;

    private Integer duration;

    public TaskTrackerDto toTaskTrackerDto(){
        return TaskTrackerDto.builder()
                .name(name)
                .startedWhen(startedWhen)
                .finishedWhen(finishedWhen)
                .duration(duration)
                .build();

    }

}
