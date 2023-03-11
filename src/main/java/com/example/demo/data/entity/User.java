package com.example.demo.data.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @ManyToMany
    @JoinTable(
            name = "user_grants",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "grant_id", referencedColumnName = "id")
    )
    private Set<Grant> grants;
}
