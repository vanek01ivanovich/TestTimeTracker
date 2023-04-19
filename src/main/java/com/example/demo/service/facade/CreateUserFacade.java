package com.example.demo.service.facade;

import com.example.demo.data.entity.User;
import com.example.demo.web.dto.UserDto;

public interface CreateUserFacade {

    void createUser(User user);

    User representUserForCreation(UserDto userDto);

}
