package com.example.conference_manager.services;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.AverageRatingDTO;
import com.example.conference_manager.models.dto.ConferenceDTO;
import com.example.conference_manager.models.dto.EventIdsDTO;
import com.example.conference_manager.models.dto.SessionDTO;
import com.example.conference_manager.models.request.ConferenceRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ConferenceService {
    List<ConferenceDTO> findAll();
    List<ConferenceDTO> findNew();
    ConferenceDTO findById(UUID id) throws NotFoundException;
    ConferenceDTO insert(ConferenceRequest conferenceRequest) throws NotFoundException;
    List<SessionDTO> findByIdWithSessions(UUID id);
    List<ConferenceDTO> findByUserId(UUID id);
    List<ConferenceDTO> findAllOrdered();
    ConferenceDTO update(UUID id, ConferenceRequest conferenceRequest) throws NotFoundException;
    UUID findIdBySession(UUID id);
    List<ConferenceDTO> findByEventId(EventIdsDTO ids);
    List<AverageRatingDTO> findAverageRatingsForConference(UUID conferenceId);
}
