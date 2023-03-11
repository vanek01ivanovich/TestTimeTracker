package com.example.demo.web.controller;

import com.example.demo.data.entity.User;
import com.example.demo.web.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get-user-by-username/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/create-user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestParam UserDto userDto){
    }

    @PatchMapping("/update-user-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable String id, @RequestParam UserDto userDto){
    }

    @DeleteMapping("/delete-user-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String id){

    }
}
