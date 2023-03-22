package com.example.demo.service.facade.impl;

import com.example.demo.data.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserProfileService;
import com.example.demo.service.UserService;
import com.example.demo.service.facade.CreateUserFacade;
import com.example.demo.web.dto.UserCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserFacadeImpl implements CreateUserFacade {

    private final UserService userService;
    private final UserProfileService userProfileService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CreateUserFacadeImpl(UserService userService,
                                UserProfileService userProfileService,
                                BCryptPasswordEncoder bCryptPasswordEncoder,
                                RoleService roleService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public void createUser(User user) {
        User createdUser = userService.createUser(user);
        userProfileService.createUserProfile(createdUser);
    }

    @Override
    public User representUserForCreation(UserCreateDto userCreateDto) {
        return User.builder()
                .password(bCryptPasswordEncoder.encode(userCreateDto.getPassword()))
                .lastName(userCreateDto.getLastName())
                .firstName(userCreateDto.getFirstName())
                .username(userCreateDto.getUsername())
                .email(userCreateDto.getEmail())
                .role(roleService.findRoleByName(userCreateDto.getRoleName()))
                .build();
    }
}
