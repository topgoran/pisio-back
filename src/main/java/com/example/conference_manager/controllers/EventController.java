package com.example.conference_manager.controllers;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.EventByVenueAndDateDTO;
import com.example.conference_manager.models.dto.EventCollisionCheckDTO;
import com.example.conference_manager.models.dto.EventDTO;
import com.example.conference_manager.models.request.EventRequest;
import com.example.conference_manager.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDTO> findAll(){
        return eventService.findAll();
    }

    @GetMapping("/moderator/{id}")
    public List<EventDTO> findAllByModerator(@PathVariable("id") UUID id){
        return eventService.findByModerator(id);
    }

    @GetMapping("/{id}")
    public EventDTO findById(@PathVariable("id")UUID id) throws NotFoundException {
        return eventService.findById(id);
    }

    @PostMapping
    public EventDTO insert(@RequestBody EventRequest eventRequest) throws NotFoundException {
        return eventService.insert(eventRequest);
    }

    @PostMapping("/check")
    public List<EventDTO> checkForCollision(@RequestBody EventCollisionCheckDTO eventCollisionCheckDTO){
        return eventService.checkForCollision(eventCollisionCheckDTO);
    }

    @PostMapping("/byvenueanddate")
    public List<EventDTO> findByVenueAndDate(@RequestBody EventByVenueAndDateDTO eventByVenueAndDateDTO){
        return eventService.getByVenueAndDate(eventByVenueAndDateDTO.getVenueId(), eventByVenueAndDateDTO.getDate());
    }

    @PutMapping("/{id}")
    public EventDTO update(@PathVariable("id") UUID id, @RequestBody EventRequest eventRequest) throws NotFoundException {
        return eventService.update(id, eventRequest);
    }
}
