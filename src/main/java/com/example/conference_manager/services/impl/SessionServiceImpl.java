package com.example.conference_manager.services.impl;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.SessionDTO;
import com.example.conference_manager.models.entities.SessionEntity;
import com.example.conference_manager.models.request.SessionRequest;
import com.example.conference_manager.repositories.SessionRepository;
import com.example.conference_manager.services.SessionService;
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
public class SessionServiceImpl implements SessionService {

    @PersistenceContext
    private EntityManager entityManager;

    private final SessionRepository sessionRepository;
    private final ModelMapper modelMapper;

    public SessionServiceImpl(SessionRepository sessionRepository, ModelMapper modelMapper) {
        this.sessionRepository = sessionRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<SessionDTO> findAll() {
        return sessionRepository.findAll().stream().map(s -> modelMapper.map(s, SessionDTO.class)).collect(Collectors.toList());
    }

    @Override
    public SessionDTO findById(UUID id) throws NotFoundException {
        return modelMapper.map(sessionRepository.findById(id).orElseThrow(NotFoundException::new), SessionDTO.class);
    }

    @Override
    public SessionDTO insert(SessionRequest sessionRequest) throws NotFoundException {
        SessionEntity sessionEntity = modelMapper.map(sessionRequest, SessionEntity.class);
        sessionEntity.setSessionId(null);
        sessionEntity = sessionRepository.saveAndFlush(sessionEntity);
        entityManager.refresh(sessionEntity);
        return findById(sessionEntity.getSessionId());
    }

    @Override
    public List<SessionDTO> findByUser(UUID id) {
        return sessionRepository.findByUser_UserIdOrderByDateAsc(id).stream().map(s -> modelMapper.map(s, SessionDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<SessionDTO> findAllOrdered() {
        return sessionRepository.findAllByOrderByDateAsc().stream().map(s -> modelMapper.map(s, SessionDTO.class)).collect(Collectors.toList());
    }

    @Override
    public SessionDTO update(UUID id, SessionRequest sessionRequest) throws NotFoundException {
        SessionEntity sessionEntity = modelMapper.map(sessionRequest, SessionEntity.class);
        sessionEntity.setSessionId(id);
        sessionEntity = sessionRepository.saveAndFlush(sessionEntity);
        entityManager.refresh(sessionEntity);
        return findById(sessionEntity.getSessionId());
    }

    @Override
    public UUID findByEvent(UUID id) {
        return sessionRepository.findByEvent(id);
    }
}
