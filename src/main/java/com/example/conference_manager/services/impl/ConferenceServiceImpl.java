package com.example.conference_manager.services.impl;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.*;
import com.example.conference_manager.models.entities.ConferenceEntity;
import com.example.conference_manager.models.request.ConferenceRequest;
import com.example.conference_manager.repositories.ConferenceRepository;
import com.example.conference_manager.repositories.SessionRepository;
import com.example.conference_manager.services.ConferenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@CrossOrigin
@Transactional
public class ConferenceServiceImpl implements ConferenceService {

    @PersistenceContext
    private EntityManager entityManager;

    private final SessionRepository sessionRepository;
    private final ConferenceRepository conferenceRepository;
    private final ModelMapper modelMapper;

    public ConferenceServiceImpl(ConferenceRepository conferenceRepository, ModelMapper modelMapper, SessionRepository sessionRepository) {
        this.conferenceRepository = conferenceRepository;
        this.modelMapper = modelMapper;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<ConferenceDTO> findAll() {
        return conferenceRepository.findAll().stream().map(c -> modelMapper.map(c, ConferenceDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ConferenceDTO> findNew() {
        return conferenceRepository.findNew().stream().map(c -> modelMapper.map(c, ConferenceDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ConferenceDTO findById(UUID id) throws NotFoundException {
        return modelMapper.map(conferenceRepository.findById(id).orElseThrow(NotFoundException::new), ConferenceDTO.class);
    }

    @Override
    public ConferenceDTO insert(ConferenceRequest conferenceRequest) throws NotFoundException {
        ConferenceEntity conferenceEntity = modelMapper.map(conferenceRequest, ConferenceEntity.class);
        conferenceEntity.setConferenceId(null);
        conferenceEntity = conferenceRepository.saveAndFlush(conferenceEntity);
        entityManager.refresh(conferenceEntity);
        return findById(conferenceEntity.getConferenceId());
    }

    @Override
    public List<SessionDTO> findByIdWithSessions(UUID id) {
        return sessionRepository.findByConference_ConferenceIdOrderByDateAsc(id).stream().map(s -> modelMapper.map(s, SessionDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ConferenceDTO> findByUserId(UUID id) {
        return conferenceRepository.findByUserCreator_UserIdOrderByDateFromAsc(id).stream().map(c -> modelMapper.map(c, ConferenceDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ConferenceDTO> findAllOrdered() {
        return conferenceRepository.findAllByOrderByDateFromAsc().stream().map(c -> modelMapper.map(c, ConferenceDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ConferenceDTO update(UUID id, ConferenceRequest conferenceRequest) throws NotFoundException {
        ConferenceEntity conferenceEntity = modelMapper.map(conferenceRequest, ConferenceEntity.class);
        conferenceEntity.setConferenceId(id);
        conferenceEntity = conferenceRepository.saveAndFlush(conferenceEntity);
        entityManager.refresh(conferenceEntity);
        return findById(conferenceEntity.getConferenceId());
    }

    @Override
    public UUID findIdBySession(UUID id) {
        return conferenceRepository.findBySession(id);
    }

    @Override
    public List<ConferenceDTO> findByEventId(EventIdsDTO ids) {
        return conferenceRepository.findByEventIds(ids.getEventIds()).stream().map(c -> modelMapper.map(c, ConferenceDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<AverageRatingDTO> findAverageRatingsForConference(UUID conferenceId) {
        return conferenceRepository.findAverageRatings(conferenceId);
    }

    @Override
    public List<GradingSubjectDTO> findGradingSubjectsForConference(UUID conferenceId) {
        return conferenceRepository.findGradingSubjectsByConferenceId(conferenceId).stream().map(g -> modelMapper.map(g, GradingSubjectDTO.class)).collect(Collectors.toList());
    }
}
