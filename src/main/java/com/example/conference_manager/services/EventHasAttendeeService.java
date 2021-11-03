package com.example.conference_manager.services;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.EventHasAttendeeDTO;
import com.example.conference_manager.models.request.EventHasAttendeeRequest;

import java.util.List;
import java.util.UUID;

public interface EventHasAttendeeService {
    List<EventHasAttendeeDTO> findAll();
    EventHasAttendeeDTO findById(UUID id) throws NotFoundException;
    EventHasAttendeeDTO insert(EventHasAttendeeRequest eventHasAttendeeRequest) throws NotFoundException;
    Integer countByEventId(UUID id);
    EventHasAttendeeDTO findByUserAndEvent(UUID userId, UUID eventId);
    void delete(UUID id);
    List<UUID> findByAttendeeId(UUID id);
}
