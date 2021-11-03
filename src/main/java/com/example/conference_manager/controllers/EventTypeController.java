package com.example.conference_manager.controllers;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.EventTypeDTO;
import com.example.conference_manager.models.request.EventTypeRequest;
import com.example.conference_manager.repositories.EventTypeRepository;
import com.example.conference_manager.services.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/eventtypes")
public class EventTypeController {

    private final EventTypeService eventTypeService;

    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @GetMapping
    public List<EventTypeDTO> findAll(){
        return eventTypeService.findAll();
    }

    @GetMapping("/{id}")
    public EventTypeDTO findById(@PathVariable("id")UUID id) throws NotFoundException {
        return eventTypeService.findById(id);
    }

    @PostMapping
    public EventTypeDTO insert(@RequestBody EventTypeRequest eventTypeRequest) throws NotFoundException {
        return  eventTypeService.insert(eventTypeRequest);
    }
}
