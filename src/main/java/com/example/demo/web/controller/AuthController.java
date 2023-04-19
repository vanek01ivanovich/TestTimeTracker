package com.example.demo.web.controller;

import com.example.demo.config.token.JwtTokenProvider;
import com.example.demo.config.token.JwtUser;
import com.example.demo.data.entity.User;
import com.example.demo.service.facade.CreateUserFacade;
import com.example.demo.web.dto.LoginUserDto;
import com.example.demo.web.dto.UserDto;
import com.example.demo.web.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CreateUserFacade createUserFacade;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody LoginUserDto request) {
        log.info("authenticateUser {} ", request.toString());
        //LoginValidator
        Authentication authenticate = null;
        try {
            authenticate =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (authenticate == null) {
            throw new RuntimeException("null");
        }
        JwtUser user = (JwtUser) authenticate.getPrincipal();
        String role = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
        String token = jwtTokenProvider.generateAccessToken(user);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(token)
                .role(role)
                .username(user.getUsername())
                .build();
        log.info(authenticationResponse.toString());
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody UserDto userDto) {
        log.info("signup {} ", userDto.toString());
        User userForCreation = createUserFacade.representUserForCreation(userDto);
        createUserFacade.createUser(userForCreation);
    }

}
