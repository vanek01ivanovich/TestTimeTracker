package com.example.demo.web.dto;

import com.example.demo.data.entity.Project;
import lombok.Data;

@Data
public class ProjectRequestDto {
    private String name;

    private Integer budget;

    public Project toProjectEntity(){
        return Project.builder()
                .name(name)
                .budget(budget)
                .build();
    }
}
