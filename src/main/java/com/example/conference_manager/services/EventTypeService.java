package com.example.conference_manager.services;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.EventTypeDTO;
import com.example.conference_manager.models.request.EventTypeRequest;

import java.util.List;
import java.util.UUID;

public interface EventTypeService {
    List<EventTypeDTO> findAll();
    EventTypeDTO findById(UUID id) throws NotFoundException;
    EventTypeDTO insert(EventTypeRequest eventTypeRequest) throws NotFoundException;
}
