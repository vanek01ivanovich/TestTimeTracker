package com.example.demo.data.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "task_type")
public class TaskType {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "taskType", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
