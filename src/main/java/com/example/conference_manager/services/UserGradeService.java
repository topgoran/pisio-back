package com.example.conference_manager.services;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.UserGradeDTO;
import com.example.conference_manager.models.request.UserGradeRequest;

import java.util.List;
import java.util.UUID;

public interface UserGradeService {
    List<UserGradeDTO> findAll();
    UserGradeDTO findById(UUID id) throws NotFoundException;
    UserGradeDTO insert(UserGradeRequest userGradeRequest) throws NotFoundException;
    UserGradeDTO findByUserAndSubject(UUID userId, UUID subjectId);

}
