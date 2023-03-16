package com.example.demo.web.validation;

import com.example.demo.web.dto.LoginUserDto;
import org.springframework.util.StringUtils;

import java.util.stream.Stream;

public class LoginValidator {

    public void validateRequest(LoginUserDto loginUserDto){
        /*Stream.of(loginUserDto.getUsername(),loginUserDto.getPassword())
                .filter(str -> !StringUtils.hasText(str))*/

    }

}
