package com.example.demo.service;

import com.example.demo.data.entity.User;
import com.example.demo.web.dto.AllUsersDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(String id);

    void deleteUserById(String id);

    void updateUser(User user);

    User getUserByUserName(String username);

    User getByEmail(String email);

    List<AllUsersDto> getAllUsers(int page, int size);

    List<User> getAllUsers(int page, int size, Sort sort);
}
