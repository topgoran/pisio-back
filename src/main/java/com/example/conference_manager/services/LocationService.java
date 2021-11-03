package com.example.conference_manager.services;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.LocationDTO;
import com.example.conference_manager.models.dto.LocationWithVenuesDTO;
import com.example.conference_manager.models.request.LocationRequest;

import java.util.List;
import java.util.UUID;

public interface LocationService {
    List<LocationDTO> findAll();
    LocationDTO findById(UUID id) throws NotFoundException;
    LocationWithVenuesDTO findByIdWithVenues(UUID id) throws NotFoundException;
    LocationDTO insert(LocationRequest locationRequest) throws NotFoundException;
    void delete(UUID id);
    LocationDTO update(UUID id, LocationRequest locationRequest) throws NotFoundException;
}
