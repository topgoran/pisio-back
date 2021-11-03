package com.example.conference_manager.services;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.GradingSubjectDTO;
import com.example.conference_manager.models.request.GradingSubjectRequest;

import java.util.List;
import java.util.UUID;

public interface GradingSubjectService {
    List<GradingSubjectDTO> findAll();
    GradingSubjectDTO findById(UUID id) throws NotFoundException;
    GradingSubjectDTO insert(GradingSubjectRequest gradingSubjectRequest) throws NotFoundException;
}
