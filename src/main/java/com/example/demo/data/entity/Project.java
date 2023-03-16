package com.example.demo.data.entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

}
