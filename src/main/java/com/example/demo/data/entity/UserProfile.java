package com.example.demo.data.entity;

import com.example.demo.data.enums.Position;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "position")
    private Position position;

    @Column(name = "age")
    private Integer age;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    @MapsId
    private User user;

    @ManyToMany
    @JoinTable(
            name = "user_profile_languages",
            joinColumns = @JoinColumn(name = "user_profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id")
    )
    private Set<Language> languages;

}
