package com.example.demo.service.impl;

import com.example.demo.data.entity.User;
import com.example.demo.data.enums.ERole;
import com.example.demo.persistence.repository.RoleRepository;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.AllUsersDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
/*    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;*/

    @Autowired
    public UserServiceImpl(UserRepository userRepository/*,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           RoleRepository roleRepository*/) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        /*User newUser = User.builder()
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("No Such Role")))
                .build();*/
        try {
            User createdUser = userRepository.saveAndFlush(user);
            //userProfileService.createUserProfile(createdUser);
            log.info("createUser done");
            return createdUser;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("User wasn`t created");
        }
    }

    @Override
    public User getUserById(String id) {
        log.info("getuserById with id {}", id);
        UUID uuid = UUID.fromString(id);
        log.info("getuserById uuid {}", uuid);
        User userById = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        log.info("userById {}", userById.getId());
        return userById;
    }

    @Override
    @Transactional
    public void deleteUserById(String id) {
        log.info("deleteUserById with id {}", id);
        User userToDelete = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));
        log.info("deleteUserById userToDelete with id {}", userToDelete.getId());
        try {
            userRepository.deleteById(UUID.fromString(id));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        log.info("deleteUserById done");
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User getUserByUserName(String username) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<AllUsersDto> getAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size))
                .getContent()
                .stream()
                .map(AllUsersDto::build)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsers(int page, int size, Sort sort) {
        Page<User> usersPage = userRepository.findAll(PageRequest.of(page, size, sort));
        return usersPage.getContent();
    }

}
