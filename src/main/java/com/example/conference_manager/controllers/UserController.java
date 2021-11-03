package com.example.conference_manager.controllers;


import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.UserDTO;
import com.example.conference_manager.models.dto.UserGradeDTO;
import com.example.conference_manager.models.request.UserRequest;
import com.example.conference_manager.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    @GetMapping("/type/{userType}")
    public List<UserDTO> findByUserType(@PathVariable("userType") String userType){
        return userService.findByUserType(userType);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable("id") UUID id) throws NotFoundException {
        return userService.findById(id);
    }

    @GetMapping("/userforconference/{userId}/{conferenceId}")
    public List<UserGradeDTO> findRatingsForConference(@PathVariable("userId") UUID userId, @PathVariable("conferenceId") UUID conferenceId){
        return userService.findUsersGradesForConference(userId, conferenceId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id){
        userService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity insert(@RequestBody UserRequest userRequest) throws NotFoundException {
        try {
            return new ResponseEntity(userService.insert(userRequest), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity("Username already exists", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable("id") UUID id, @RequestBody UserRequest userRequest) throws NotFoundException {
        return userService.update(id, userRequest);
    }

}
