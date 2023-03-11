package com.example.demo.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "task_type")
public class TaskType {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "taskType")
    private Task task;
}
