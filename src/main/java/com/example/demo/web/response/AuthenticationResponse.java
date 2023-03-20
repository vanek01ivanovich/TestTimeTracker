package com.example.demo.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

    private String token;

    private String role;

    private String username;
}
