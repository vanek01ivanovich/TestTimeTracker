package com.example.demo.data.entity;

import com.example.demo.data.enums.Status;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "task_tracker")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskTracker {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "started_when")
    @NotNull
    private Date startedWhen;

    @Column(name = "finished_when")
    private Date finishedWhen;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    @MapsId
    private Task task;
}
