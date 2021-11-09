package com.example.conference_manager.services.impl;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.EventCollisionCheckDTO;
import com.example.conference_manager.models.dto.EventDTO;
import com.example.conference_manager.models.dto.ResourceDTO;
import com.example.conference_manager.models.dto.ResourceWithNumberDTO;
import com.example.conference_manager.models.entities.EventEntity;
import com.example.conference_manager.models.request.EventRequest;
import com.example.conference_manager.repositories.EventRepository;
import com.example.conference_manager.services.EventService;
import jdk.jfr.Event;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @PersistenceContext
    private EntityManager entityManager;

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EventDTO> findAll() {
        return eventRepository.findAll().stream().map(e -> modelMapper.map(e, EventDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EventDTO findById(UUID id) throws NotFoundException {
        return modelMapper.map(eventRepository.findById(id).orElseThrow(NotFoundException::new), EventDTO.class);
    }

    @Override
    public EventDTO insert(EventRequest eventRequest) throws NotFoundException {
        EventEntity eventEntity = modelMapper.map(eventRequest, EventEntity.class);
        eventEntity.setEventId(null);
        eventEntity = eventRepository.saveAndFlush(eventEntity);
        entityManager.refresh(eventEntity);
        return findById(eventEntity.getEventId());
    }

    @Override
    public List<EventDTO> findByModerator(UUID id) {
        return eventRepository.findByUserModerator_UserIdOrderByTimeFromAsc(id).stream().map(e -> modelMapper.map(e, EventDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EventDTO update(UUID id, EventRequest eventRequest) throws NotFoundException {
        EventEntity eventEntity = modelMapper.map(eventRequest, EventEntity.class);
        eventEntity.setEventId(id);
        eventEntity = eventRepository.saveAndFlush(eventEntity);
        entityManager.refresh(eventEntity);
        return findById(eventEntity.getEventId());
    }

    @Override
    public List<EventDTO> checkForCollision(EventCollisionCheckDTO eventCollisionCheckDTO) {
        return eventRepository.findByVenueVenueIdAndTimeFromBetweenOrTimeToBetween(eventCollisionCheckDTO.getVenueId(), eventCollisionCheckDTO.getTimeFrom(), eventCollisionCheckDTO.getTimeTo()).stream().map(e -> modelMapper.map(e, EventDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getByVenueAndDate(UUID venueId, Timestamp date) {
        return eventRepository.findEventsByVenueAndDate(venueId, date).stream().map(e -> modelMapper.map(e, EventDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ResourceWithNumberDTO> findResourcesByEvent(UUID id) {
        return eventRepository.findResourcesForEntity(id);
    }
}
