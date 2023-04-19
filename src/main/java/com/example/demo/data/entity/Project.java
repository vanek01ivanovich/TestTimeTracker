package com.example.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "project",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"name"}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"tasks"})
public class Project {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "budget")
    @NotNull
    private Integer budget;

    @JsonIgnore
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Task> tasks;

}
