package com.example.conference_manager.controllers;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.VenueDTO;
import com.example.conference_manager.models.request.VenueRequest;
import com.example.conference_manager.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    List<VenueDTO> findAll(){
        return venueService.findAll();
    }

    @GetMapping("/{id}")
    VenueDTO findById(@PathVariable("id")UUID id) throws NotFoundException {
        return venueService.findById(id);
    }

    @GetMapping("/location/{id}")
    List<VenueDTO> findByLocation(@PathVariable("id") UUID id){
        return venueService.findByLocation(id);
    }

    @PostMapping
    VenueDTO insert(@RequestBody VenueRequest venueRequest) throws NotFoundException {
        return venueService.insert(venueRequest);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") UUID id){
        venueService.delete(id);
    }

    @PutMapping("/{id}")
    VenueDTO update(@PathVariable("id") UUID id, @RequestBody VenueRequest venueRequest) throws NotFoundException {
        return venueService.update(id, venueRequest);
    }
}
