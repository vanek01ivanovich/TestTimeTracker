package com.example.demo.service.facade;

import com.example.demo.data.entity.User;
import com.example.demo.web.dto.UserCreateDto;

public interface CreateUserFacade {

    void createUser(User user);

    User representUserForCreation(UserCreateDto userCreateDto);
}
