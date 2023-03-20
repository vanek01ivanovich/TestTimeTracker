package com.example.demo.web.dto;

import com.example.demo.data.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class AllUsersDto {
    private UUID id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    public static AllUsersDto build(User user){
        return AllUsersDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
