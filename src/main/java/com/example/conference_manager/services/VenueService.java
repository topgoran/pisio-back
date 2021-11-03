package com.example.conference_manager.services;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.VenueDTO;
import com.example.conference_manager.models.request.VenueRequest;

import java.util.List;
import java.util.UUID;

public interface VenueService {
    List<VenueDTO> findAll();
    VenueDTO findById(UUID id) throws NotFoundException;
    VenueDTO insert(VenueRequest venueRequest) throws NotFoundException;
    List<VenueDTO> findByLocation(UUID locationId);
    void delete(UUID id);
    VenueDTO update(UUID id, VenueRequest venueRequest) throws NotFoundException;
}
