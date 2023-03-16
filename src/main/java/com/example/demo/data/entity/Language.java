package com.example.demo.data.entity;

import com.example.demo.data.enums.ELanguage;
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
    @Enumerated(EnumType.STRING)
    private ELanguage name;

    @ManyToMany(mappedBy = "languages")
    private Set<UserProfile> userProfiles;
}
