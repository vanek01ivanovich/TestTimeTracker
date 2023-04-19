package com.example.demo.service.facade.impl;

import com.example.demo.data.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserProfileService;
import com.example.demo.service.UserService;
import com.example.demo.service.facade.CreateUserFacade;
import com.example.demo.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserFacadeImpl implements CreateUserFacade {

    private final UserService userService;
    private final UserProfileService userProfileService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional
    public void createUser(User user) {
        User createdUser = userService.createUser(user);
        userProfileService.createUserProfile(createdUser);
    }

    @Override
    public User representUserForCreation(UserDto userDto) {
        return User.builder()
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .lastName(userDto.getLastName())
                .firstName(userDto.getFirstName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .role(roleService.findRoleByName(userDto.getRoleName()))
                .build();
    }
}
