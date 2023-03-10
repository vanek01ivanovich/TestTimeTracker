package com.example.demo.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "budget")
    private Integer budget;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

}
