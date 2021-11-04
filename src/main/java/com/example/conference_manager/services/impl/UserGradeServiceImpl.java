package com.example.conference_manager.services.impl;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.UserGradeDTO;
import com.example.conference_manager.models.entities.GradingSubjectEntity;
import com.example.conference_manager.models.entities.GradingSubjectHasUserEntity;
import com.example.conference_manager.models.request.UserGradeRequest;
import com.example.conference_manager.repositories.UserGradeRepository;
import com.example.conference_manager.services.UserGradeService;
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
public class UserGradeServiceImpl implements UserGradeService {
    @PersistenceContext
    private EntityManager entityManager;

    private final ModelMapper modelMapper;
    private final UserGradeRepository userGradeRepository;

    public UserGradeServiceImpl(ModelMapper modelMapper, UserGradeRepository userGradeRepository) {
        this.modelMapper = modelMapper;
        this.userGradeRepository = userGradeRepository;
    }

    @Override
    public List<UserGradeDTO> findAll() {
        return userGradeRepository.findAll().stream().map(u -> modelMapper.map(u, UserGradeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserGradeDTO findById(UUID id) throws NotFoundException {
        return modelMapper.map(userGradeRepository.findById(id).orElseThrow(NotFoundException::new), UserGradeDTO.class);
    }

    @Override
    public UserGradeDTO insert(UserGradeRequest userGradeRequest) throws NotFoundException {
        GradingSubjectHasUserEntity gradingSubjectHasUserEntity = modelMapper.map(userGradeRequest, GradingSubjectHasUserEntity.class);
        gradingSubjectHasUserEntity.setGradeId(null);
        gradingSubjectHasUserEntity = userGradeRepository.saveAndFlush(gradingSubjectHasUserEntity);
        entityManager.refresh(gradingSubjectHasUserEntity);
        return findById(gradingSubjectHasUserEntity.getGradeId());
    }

    @Override
    public UserGradeDTO findByUserAndSubject(UUID userId, UUID subjectId) {
        return modelMapper.map(userGradeRepository.findByUserUserIdAndGradingSubjectGradingSubjectId(userId, subjectId), UserGradeDTO.class);
    }

    @Override
    public UserGradeDTO update(UUID id, UserGradeRequest userGradeRequest) throws NotFoundException {
        GradingSubjectHasUserEntity gradingSubjectHasUserEntity = modelMapper.map(userGradeRequest, GradingSubjectHasUserEntity.class);
        gradingSubjectHasUserEntity.setGradeId(id);
        gradingSubjectHasUserEntity = userGradeRepository.saveAndFlush(gradingSubjectHasUserEntity);
        entityManager.refresh(gradingSubjectHasUserEntity);
        return findById(gradingSubjectHasUserEntity.getGradeId());
    }
}
