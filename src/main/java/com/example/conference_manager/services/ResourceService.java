package com.example.conference_manager.services;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.ResourceDTO;
import com.example.conference_manager.models.dto.ResourceTypeDTO;
import com.example.conference_manager.models.request.ResourceRequest;

import java.util.List;
import java.util.UUID;

public interface ResourceService {

    List<ResourceDTO> findAll();
    ResourceDTO findById(UUID id) throws NotFoundException;
    ResourceDTO insert(ResourceRequest resourceRequest) throws NotFoundException;
    List<ResourceDTO> findAllByOrdered();
    void delete(UUID id);
    ResourceDTO update(UUID id, ResourceRequest resourceRequest) throws NotFoundException;
    List<ResourceTypeDTO> findResourceTypes();
}
