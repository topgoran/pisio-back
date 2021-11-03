package com.example.conference_manager.controllers;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.GradingSubjectDTO;
import com.example.conference_manager.models.request.GradingSubjectRequest;
import com.example.conference_manager.services.GradingSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/gradingsubjects")
public class GradingSubjectController {

    private final GradingSubjectService gradingSubjectService;

    public GradingSubjectController(GradingSubjectService gradingSubjectService) {
        this.gradingSubjectService = gradingSubjectService;
    }

    @GetMapping
    public List<GradingSubjectDTO> findAll(){
        return gradingSubjectService.findAll();
    }

    @GetMapping("/{id}")
    public GradingSubjectDTO findById(@PathVariable("id")UUID id) throws NotFoundException {
        return gradingSubjectService.findById(id);
    }

    @PostMapping
    public GradingSubjectDTO insert(@RequestBody GradingSubjectRequest gradingSubjectRequest) throws NotFoundException {
        return gradingSubjectService.insert(gradingSubjectRequest);
    }
}
