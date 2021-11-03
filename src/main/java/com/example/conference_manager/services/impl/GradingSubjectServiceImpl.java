package com.example.conference_manager.services.impl;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.GradingSubjectDTO;
import com.example.conference_manager.models.entities.GradingSubjectEntity;
import com.example.conference_manager.models.request.GradingSubjectRequest;
import com.example.conference_manager.repositories.GradingSubjectRepository;
import com.example.conference_manager.services.GradingSubjectService;
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
public class GradingSubjectServiceImpl implements GradingSubjectService {

    @PersistenceContext
    private EntityManager entityManager;

    final GradingSubjectRepository gradingSubjectRepository;
    final ModelMapper modelMapper;

    public GradingSubjectServiceImpl(GradingSubjectRepository gradingSubjectRepository, ModelMapper modelMapper) {
        this.gradingSubjectRepository = gradingSubjectRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GradingSubjectDTO> findAll() {
        return gradingSubjectRepository.findAll().stream().map(g -> modelMapper.map(g, GradingSubjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public GradingSubjectDTO findById(UUID id) throws NotFoundException {
        return modelMapper.map(gradingSubjectRepository.findById(id).orElseThrow(NotFoundException::new), GradingSubjectDTO.class);
    }

    @Override
    public GradingSubjectDTO insert(GradingSubjectRequest gradingSubjectRequest) throws NotFoundException {
        GradingSubjectEntity gradingSubjectEntity = modelMapper.map(gradingSubjectRequest, GradingSubjectEntity.class);
        gradingSubjectEntity.setGradingSubjectId(null);
        gradingSubjectEntity = gradingSubjectRepository.saveAndFlush(gradingSubjectEntity);
        entityManager.refresh(gradingSubjectEntity);
        return findById(gradingSubjectEntity.getGradingSubjectId());
    }
}
