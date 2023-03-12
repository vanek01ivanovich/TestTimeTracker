package com.example.demo.data.entity;

import com.example.demo.data.enums.ERole;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;

    @OneToOne(mappedBy = "role")
    private User user;
}
