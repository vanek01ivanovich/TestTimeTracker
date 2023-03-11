package com.example.demo.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "languages")
    private Set<UserProfile> userProfiles;
}
