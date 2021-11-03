package com.example.conference_manager.controllers;

import com.example.conference_manager.models.request.UserLogin;
import com.example.conference_manager.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody UserLogin userLogin){
        try {
            return new ResponseEntity(userService.findByUsernameAndPassword(userLogin), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Wrong username and password combination", HttpStatus.NOT_FOUND);
        }
    }
}
