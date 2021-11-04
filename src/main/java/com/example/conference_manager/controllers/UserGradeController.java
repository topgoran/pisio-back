package com.example.conference_manager.controllers;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.UserGradeDTO;
import com.example.conference_manager.models.request.UserGradeRequest;
import com.example.conference_manager.services.UserGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/usergrades")
public class UserGradeController {

    private final UserGradeService userGradeService;


    public UserGradeController(UserGradeService userGradeService) {
        this.userGradeService = userGradeService;
    }

    @GetMapping
    public List<UserGradeDTO> findAll(){
        return userGradeService.findAll();
    }

    @GetMapping("/{id}")
    public UserGradeDTO findById(@PathVariable("id")UUID id) throws NotFoundException {
        return userGradeService.findById(id);
    }

    @GetMapping("/byuserandsubject/{userId}/{subjectId}")
    public ResponseEntity findByUserAndSubject(@PathVariable("userId") UUID userId, @PathVariable("subjectId") UUID subjectId){
        try {
            return new ResponseEntity(userGradeService.findByUserAndSubject(userId, subjectId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public UserGradeDTO insert(@RequestBody UserGradeRequest userGradeRequest) throws NotFoundException {
        return userGradeService.insert(userGradeRequest);
    }

    @PutMapping("/{id}")
    public UserGradeDTO update(@PathVariable("id") UUID id, @RequestBody UserGradeRequest userGradeRequest) throws NotFoundException {
        return userGradeService.update(id, userGradeRequest);
    }
}
