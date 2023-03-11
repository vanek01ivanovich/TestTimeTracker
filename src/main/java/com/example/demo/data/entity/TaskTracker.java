package com.example.demo.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "task_tracker")
public class TaskTracker {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "started_when")
    private Date startedWhen;

    @Column(name = "finished_when")
    private Date finishedWhen;

    @Column(name = "duration")
    private Integer duration;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    @MapsId
    private Task task;
}
