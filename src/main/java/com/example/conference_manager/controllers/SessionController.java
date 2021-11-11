package com.example.conference_manager.controllers;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.ConferenceDTO;
import com.example.conference_manager.models.dto.SessionDTO;
import com.example.conference_manager.models.request.SessionRequest;
import com.example.conference_manager.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/sessions")
public class SessionController {


    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<SessionDTO> findAll(){
        return sessionService.findAllOrdered();
    }

    @GetMapping("/moderator/{id}")
    public List<SessionDTO> findByUserId(@PathVariable("id") UUID id){
        return sessionService.findByUser(id);
    }

    @GetMapping("/{id}")
    public SessionDTO findById(@PathVariable("id")UUID id) throws NotFoundException {
        return sessionService.findById(id);
    }

    @GetMapping("/byevent/{id}")
    public UUID findByEvent(@PathVariable("id") UUID id){
        return sessionService.findByEvent(id);
    }

    @GetMapping("findconference/{id}")
    public ConferenceDTO findConference(@PathVariable("id") UUID id){
        return sessionService.findConference(id);
    }

    @PostMapping
    public SessionDTO insert(@RequestBody SessionRequest sessionRequest) throws NotFoundException {
        return sessionService.insert(sessionRequest);
    }

    @PutMapping("/{id}")
    public SessionDTO update(@PathVariable("id") UUID id, @RequestBody SessionRequest sessionRequest) throws NotFoundException {
        return sessionService.update(id, sessionRequest);
    }
}
