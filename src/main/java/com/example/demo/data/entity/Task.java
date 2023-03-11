package com.example.demo.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private TaskType taskType;

    @OneToOne(mappedBy = "task")
    private TaskTracker taskTracker;

}
