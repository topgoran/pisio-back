package com.example.conference_manager.controllers;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.*;
import com.example.conference_manager.models.request.ConferenceRequest;
import com.example.conference_manager.services.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/conferences")
public class ConferenceController {

    final
    ConferenceService conferenceService;

    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping
    public List<ConferenceDTO> findAll(){
        return conferenceService.findAllOrdered();
    }

    @GetMapping("/new")
    public List<ConferenceDTO> findNew(){
        return conferenceService.findNew();
    }

    @GetMapping("/creator/{id}")
    public List<ConferenceDTO> findAllByUserId(@PathVariable("id") UUID id) { return  conferenceService.findByUserId(id); }

    @GetMapping("/{id}")
    public ConferenceDTO findById(@PathVariable("id")UUID id) throws NotFoundException {
        return conferenceService.findById(id);
    }

    @GetMapping("/{id}/sessions")
    public List<SessionDTO> findByIdWithSessions(@PathVariable("id")UUID id){
        return conferenceService.findByIdWithSessions(id);
    }

    @GetMapping("/bysession/{id}")
    public UUID findBySession(@PathVariable("id") UUID id){
        return conferenceService.findIdBySession(id);
    }

    @GetMapping("/avg/{id}")
    public List<AverageRatingDTO> findAvgRatingsForConference(@PathVariable("id") UUID id){
        return conferenceService.findAverageRatingsForConference(id);
    }

    @GetMapping("/gradingsubjects/{id}")
    public List<GradingSubjectDTO> findGradingSubjectsByConferenceId(@PathVariable("id") UUID id){
        return conferenceService.findGradingSubjectsForConference(id);
    }

    @PostMapping("/byeventids")
    public List<ConferenceDTO> findByEventIds(@RequestBody EventIdsDTO ids){
        return conferenceService.findByEventId(ids);
    }

    @PostMapping
    public ConferenceDTO insert(@RequestBody ConferenceRequest conferenceRequest) throws NotFoundException {
        return conferenceService.insert(conferenceRequest);
    }

    @PutMapping("/{id}")
    public ConferenceDTO update(@PathVariable("id")UUID id, @RequestBody ConferenceRequest conferenceRequest) throws NotFoundException {
        return conferenceService.update(id, conferenceRequest);
    }
}
