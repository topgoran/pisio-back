package com.example.conference_manager.services.impl;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.EventHasAttendeeDTO;
import com.example.conference_manager.models.entities.EventHasAttendeeEntity;
import com.example.conference_manager.models.request.EventHasAttendeeRequest;
import com.example.conference_manager.repositories.EventHasAttendeeRepository;
import com.example.conference_manager.services.EventHasAttendeeService;
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
public class EventHasAttendeeServiceImpl implements EventHasAttendeeService {

    @PersistenceContext
    private EntityManager entityManager;

    private final EventHasAttendeeRepository eventHasAttendeeRepository;
    private final ModelMapper modelMapper;


    public EventHasAttendeeServiceImpl(EventHasAttendeeRepository eventHasAttendeeRepository, ModelMapper modelMapper) {
        this.eventHasAttendeeRepository = eventHasAttendeeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<EventHasAttendeeDTO> findAll() {
        return eventHasAttendeeRepository.findAll().stream().map(e -> modelMapper.map(e, EventHasAttendeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EventHasAttendeeDTO findById(UUID id) throws NotFoundException {
        return modelMapper.map(eventHasAttendeeRepository.findById(id).orElseThrow(NotFoundException::new), EventHasAttendeeDTO.class);
    }

    @Override
    public EventHasAttendeeDTO insert(EventHasAttendeeRequest eventHasAttendeeRequest) throws NotFoundException {
        EventHasAttendeeEntity eventHasAttendeeEntity = modelMapper.map(eventHasAttendeeRequest, EventHasAttendeeEntity.class);
        eventHasAttendeeEntity.setEventHasAttendee(null);
        eventHasAttendeeEntity = eventHasAttendeeRepository.saveAndFlush(eventHasAttendeeEntity);
        entityManager.refresh(eventHasAttendeeEntity);
        return findById(eventHasAttendeeEntity.getEventHasAttendee());
    }

    @Override
    public Integer countByEventId(UUID id) {
        return eventHasAttendeeRepository.countAllByEventEventId(id);
    }

    @Override
    public EventHasAttendeeDTO findByUserAndEvent(UUID userId, UUID eventId) {
        return modelMapper.map(eventHasAttendeeRepository.findByUserUserIdAndEventEventId(userId, eventId), EventHasAttendeeDTO.class);
    }

    @Override
    public void delete(UUID id) {
        eventHasAttendeeRepository.deleteById(id);
    }

    @Override
    public List<UUID> findByAttendeeId(UUID id) {
        return eventHasAttendeeRepository.findByUserUserId(id);
    }
}
