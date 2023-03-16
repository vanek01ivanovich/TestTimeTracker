package com.example.demo.data.entity;


import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Setter
@Getter
@Table(name = "users",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"email", "username"}))
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "username")
    @NotNull
    private String username;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @NotNull
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
