package com.example.demo.web.controller;

import com.example.demo.data.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.service.facade.CreateUserFacade;
import com.example.demo.web.dto.AllUsersDto;
import com.example.demo.web.dto.UserDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CreateUserFacade createUserFacade;

    @GetMapping("/username/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
        return new ResponseEntity<>(userService.getUserByUserName(userName), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.getByEmail(email), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        System.out.println("here");
        User userById = userService.getUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {
        log.info("signup {} ", userDto.toString());
        User userForCreation = createUserFacade.representUserForCreation(userDto);
        createUserFacade.createUser(userForCreation);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        log.info("updateUser with id {} and userDto {}", id, userDto);
        userService.updateUser(UUID.fromString(id), userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AllUsersDto>> getAllUsers(@RequestParam int page,
                                                         @RequestParam int size) {
        List<AllUsersDto> allUsers = userService.getAllUsers(page, size);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/getall-with-sort")
    //todo make sorting
    public ResponseEntity<List<User>> getAllUsers(@RequestParam int page,
                                                  @RequestParam int size,
                                                  @RequestParam Sort sort) {
        List<User> allUsers = userService.getAllUsers(page, size, sort);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
