package com.example.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"user", "project", "taskType", "taskTracker"})
public class Task {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "created_when")
    @NotNull
    private Date createdWhen;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @NotNull
    private Project project;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @NotNull
    private TaskType taskType;

    @JsonIgnore
    @OneToOne(mappedBy = "task", cascade = CascadeType.ALL)
    private TaskTracker taskTracker;

    public void setTaskTracker(TaskTracker taskTracker) {
        taskTracker.setTask(this);
        this.taskTracker = taskTracker;
    }


}
