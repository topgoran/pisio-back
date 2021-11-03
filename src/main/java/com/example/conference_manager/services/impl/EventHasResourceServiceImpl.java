package com.example.conference_manager.services.impl;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.EventHasResourceDTO;
import com.example.conference_manager.models.entities.EventHasResourceEntity;
import com.example.conference_manager.models.request.EventHasResourceRequest;
import com.example.conference_manager.repositories.EventHasResourceRepository;
import com.example.conference_manager.services.EventHasResourceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventHasResourceServiceImpl implements EventHasResourceService {

    @PersistenceContext
    private EntityManager entityManager;

    private final EventHasResourceRepository eventHasResourceRepository;
    private final ModelMapper modelMapper;

    public EventHasResourceServiceImpl(EventHasResourceRepository eventHasResourceRepository, ModelMapper modelMapper) {
        this.eventHasResourceRepository = eventHasResourceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EventHasResourceDTO> findAll() {
        return eventHasResourceRepository.findAll().stream().map(e -> modelMapper.map(e, EventHasResourceDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EventHasResourceDTO findById(UUID id) throws NotFoundException {
        return modelMapper.map(eventHasResourceRepository.findById(id).orElseThrow(NotFoundException::new), EventHasResourceDTO.class);
    }

    @Override
    public EventHasResourceDTO insert(EventHasResourceRequest eventHasResourceRequest) throws NotFoundException {
        EventHasResourceEntity eventHasResourceEntity = modelMapper.map(eventHasResourceRequest, EventHasResourceEntity.class);
        eventHasResourceEntity.setEventResourceId(null);
        eventHasResourceEntity = eventHasResourceRepository.saveAndFlush(eventHasResourceEntity);
        entityManager.refresh(eventHasResourceEntity);
        return findById(eventHasResourceEntity.getEventResourceId());
    }
}
