package com.example.conference_manager.services;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.EventCollisionCheckDTO;
import com.example.conference_manager.models.dto.EventDTO;
import com.example.conference_manager.models.dto.ResourceDTO;
import com.example.conference_manager.models.dto.ResourceWithNumberDTO;
import com.example.conference_manager.models.request.EventRequest;
import org.w3c.dom.events.Event;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface EventService {
    List<EventDTO> findAll();
    EventDTO findById(UUID id) throws NotFoundException;
    EventDTO insert(EventRequest eventRequest) throws NotFoundException;
    List<EventDTO> findByModerator(UUID id);
    EventDTO update(UUID id, EventRequest eventRequest) throws NotFoundException;

    List<EventDTO> checkForCollision(EventCollisionCheckDTO eventCollisionCheckDTO);
    List<EventDTO> getByVenueAndDate(UUID venueId, Timestamp date);
    List<ResourceWithNumberDTO> findResourcesByEvent(UUID id);
}
