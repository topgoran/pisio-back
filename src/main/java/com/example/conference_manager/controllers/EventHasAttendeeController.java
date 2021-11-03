package com.example.conference_manager.controllers;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.EventHasAttendeeDTO;
import com.example.conference_manager.models.entities.EventHasAttendeeEntity;
import com.example.conference_manager.models.request.EventHasAttendeeRequest;
import com.example.conference_manager.services.EventHasAttendeeService;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/eventhasattendees")
public class EventHasAttendeeController {
    private final EventHasAttendeeService eventHasAttendeeService;


    public EventHasAttendeeController(EventHasAttendeeService eventHasAttendeeService) {
        this.eventHasAttendeeService = eventHasAttendeeService;
    }

    @GetMapping
    public List<EventHasAttendeeDTO> findAll(){
        return eventHasAttendeeService.findAll();
    }

    @GetMapping("/{id}")
    public EventHasAttendeeDTO findById(@PathVariable("id") UUID id) throws NotFoundException {
        return eventHasAttendeeService.findById(id);
    }

    @GetMapping("/countattendees/{id}")
    public Integer getNumberOfAttendees(@PathVariable("id") UUID id){
        return eventHasAttendeeService.countByEventId(id);
    }

    @GetMapping("/checkifattendee/{eventId}/{userId}")
    public ResponseEntity checkIfAttendee(@PathVariable("eventId") UUID eventId, @PathVariable("userId") UUID userId){
        try{
            return new ResponseEntity(eventHasAttendeeService.findByUserAndEvent(userId, eventId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not attendee", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findbyattendee/{id}")
    public List<UUID> findByAttendee(@PathVariable("id") UUID id){
        return eventHasAttendeeService.findByAttendeeId(id);
    }

    @PostMapping
    public EventHasAttendeeDTO insert(@RequestBody EventHasAttendeeRequest eventHasAttendeeRequest) throws NotFoundException {
        return eventHasAttendeeService.insert(eventHasAttendeeRequest);
    }

    @GetMapping("/delete/{eventId}/{userId}")
    public Boolean delete(@PathVariable("eventId") UUID eventId, @PathVariable("userId") UUID userId){
        EventHasAttendeeDTO eventHasAttendeeDTO = eventHasAttendeeService.findByUserAndEvent(userId, eventId);
        if(eventHasAttendeeDTO != null){
            eventHasAttendeeService.delete(eventHasAttendeeDTO.getEventHasAttendee());
            return true;
        }
        return false;
    }
}
