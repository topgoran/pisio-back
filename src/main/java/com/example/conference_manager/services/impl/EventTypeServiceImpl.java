package com.example.conference_manager.services.impl;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.EventTypeDTO;
import com.example.conference_manager.models.entities.EventTypeEntity;
import com.example.conference_manager.models.request.EventTypeRequest;
import com.example.conference_manager.repositories.EventTypeRepository;
import com.example.conference_manager.services.EventTypeService;
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
public class EventTypeServiceImpl implements EventTypeService {

    @PersistenceContext
    EntityManager entityManager;

    private final EventTypeRepository eventTypeRepository;
    private final ModelMapper modelMapper;

    public EventTypeServiceImpl(EventTypeRepository eventTypeRepository, ModelMapper modelMapper) {
        this.eventTypeRepository = eventTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EventTypeDTO> findAll() {
        return eventTypeRepository.findAll().stream().map(e -> modelMapper.map(e, EventTypeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EventTypeDTO findById(UUID id) throws NotFoundException {
        return modelMapper.map(eventTypeRepository.findById(id).orElseThrow(NotFoundException::new), EventTypeDTO.class);
    }

    @Override
    public EventTypeDTO insert(EventTypeRequest eventTypeRequest) throws NotFoundException {
        EventTypeEntity eventTypeEntity = modelMapper.map(eventTypeRequest, EventTypeEntity.class);
        eventTypeEntity.setEventTypeId(null);
        eventTypeEntity = eventTypeRepository.saveAndFlush(eventTypeEntity);
        entityManager.refresh(eventTypeEntity);
        return findById(eventTypeEntity.getEventTypeId());
    }
}
