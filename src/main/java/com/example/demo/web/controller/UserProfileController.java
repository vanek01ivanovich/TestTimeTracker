package com.example.demo.web.controller;

import com.example.demo.data.entity.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

    @GetMapping("/get/{id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable String id){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserProfileById(@PathVariable String id, @RequestParam UserProfile userProfile){
    }
}
