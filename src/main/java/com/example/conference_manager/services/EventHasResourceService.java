package com.example.conference_manager.services;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.EventHasResourceDTO;
import com.example.conference_manager.models.request.EventHasResourceRequest;

import java.util.List;
import java.util.UUID;

public interface EventHasResourceService {
    List<EventHasResourceDTO> findAll();
    EventHasResourceDTO findById(UUID id) throws NotFoundException;
    EventHasResourceDTO insert(EventHasResourceRequest eventHasResourceRequest) throws NotFoundException;
}
