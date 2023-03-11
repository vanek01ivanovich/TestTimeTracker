package com.example.demo.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "role")
    private User user;
}
