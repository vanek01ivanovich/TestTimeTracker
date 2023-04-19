package com.example.demo.service.impl;

import com.example.demo.data.entity.User;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.service.UserProfileService;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.AllUsersDto;
import com.example.demo.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User createUser(User user) {
        try {
            User createdUser = userRepository.saveAndFlush(user);
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
    @Transactional
    public void updateUser(UUID id, UserDto userDto) {
        User userForUpdate = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found with id " + id));
        userForUpdate.setUsername(userDto.getUsername());
        userForUpdate.setEmail(userDto.getEmail());
        userForUpdate.setFirstName(userDto.getFirstName());
        userForUpdate.setLastName(userDto.getLastName());
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("user with username " + username + " is not found"));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new RuntimeException("user with email " + email + " is not found"));
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
