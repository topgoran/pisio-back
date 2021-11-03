package com.example.conference_manager.controllers;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.EventHasResourceDTO;
import com.example.conference_manager.models.request.EventHasResourceRequest;
import com.example.conference_manager.services.EventHasResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/eventhasresources")
public class EventHasResourceController {

    private final EventHasResourceService eventHasResourceService;

    public EventHasResourceController(EventHasResourceService eventHasResourceService) {
        this.eventHasResourceService = eventHasResourceService;
    }

    @GetMapping
    public List<EventHasResourceDTO> findAll(){
        return eventHasResourceService.findAll();
    }

    @GetMapping("/{id}")
    public EventHasResourceDTO findById(@PathVariable("id")UUID id) throws NotFoundException {
        return eventHasResourceService.findById(id);
    }

    @PostMapping
    public EventHasResourceDTO insert(@RequestBody EventHasResourceRequest eventHasResourceRequest) throws NotFoundException {
        return eventHasResourceService.insert(eventHasResourceRequest);
    }
}
