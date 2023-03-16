package com.example.demo.web.dto;

import com.example.demo.data.entity.User;
import lombok.Data;

@Data
public class UserCreateDto {

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    public User toUserEntity(){
        return User.builder()
                .username(username)
                .email(email)
                .firstName(firstName)
                .password(password)
                .lastName(lastName)
                .build();
    }

}
