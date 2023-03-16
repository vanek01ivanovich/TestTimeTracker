package com.example.demo.service.impl;

import com.example.demo.data.entity.User;
import com.example.demo.data.enums.ERole;
import com.example.demo.persistence.repository.RoleRepository;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.service.UserProfileService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserProfileService userProfileService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           RoleRepository roleRepository,
                           UserProfileService userProfileService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userProfileService = userProfileService;
    }

    @Override
    @Transactional
    public void createUser(User user) {
        User newUser = User.builder()
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("No Such Role")))
                .build();
        try {
            User createdUser = userRepository.saveAndFlush(newUser);
            userProfileService.createUserProfile(createdUser);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        log.info("createUser done");
    }

}
