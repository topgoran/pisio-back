package com.example.conference_manager.controllers;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.LocationDTO;
import com.example.conference_manager.models.dto.LocationWithVenuesDTO;
import com.example.conference_manager.models.request.LocationRequest;
import com.example.conference_manager.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/locations")
public class LocationController {

    final
    LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationDTO> findAll(){
        return locationService.findAll();
    }

    @GetMapping("/{id}")
    public LocationDTO findById(@PathVariable("id")UUID id) throws NotFoundException {
        return locationService.findById(id);
    }

    @GetMapping("/{id}/venues")
    public LocationWithVenuesDTO findByIdWithVenues(@PathVariable("id") UUID id) throws NotFoundException {
        return locationService.findByIdWithVenues(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDTO insert(@RequestBody LocationRequest locationRequest) throws NotFoundException {
        return locationService.insert(locationRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id){
        locationService.delete(id);
    }

    @PutMapping("/{id}")
    public LocationDTO update(@PathVariable("id") UUID id, @RequestBody LocationRequest locationRequest) throws NotFoundException {
        return locationService.update(id, locationRequest);
    }
}
