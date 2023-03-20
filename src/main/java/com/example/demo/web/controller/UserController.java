package com.example.demo.web.controller;

import com.example.demo.data.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.web.UserDto;
import com.example.demo.web.dto.AllUsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-user-by-username/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/get-user-by-email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        System.out.println("here");
        User userById = userService.getUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @PostMapping("/create-user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestParam UserDto userDto) {
    }

    @PatchMapping("/update-user-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable String id, @RequestParam UserDto userDto) {
    }

    @DeleteMapping("/delete-user-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/getall")
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
