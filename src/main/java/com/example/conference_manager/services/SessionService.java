package com.example.conference_manager.services;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.SessionDTO;
import com.example.conference_manager.models.request.SessionRequest;

import java.util.List;
import java.util.UUID;

public interface SessionService {
    List<SessionDTO> findAll();
    SessionDTO findById(UUID id) throws NotFoundException;
    SessionDTO insert(SessionRequest sessionRequest) throws NotFoundException;
    List<SessionDTO> findByUser(UUID id);
    List<SessionDTO> findAllOrdered();
    SessionDTO update(UUID id, SessionRequest sessionRequest) throws NotFoundException;
    UUID findByEvent(UUID id);
}
