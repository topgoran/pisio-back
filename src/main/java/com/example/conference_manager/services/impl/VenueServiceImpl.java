package com.example.conference_manager.services.impl;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.VenueDTO;
import com.example.conference_manager.models.entities.VenueEntity;
import com.example.conference_manager.models.request.VenueRequest;
import com.example.conference_manager.repositories.VenueRepository;
import com.example.conference_manager.services.VenueService;
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
public class VenueServiceImpl implements VenueService {

    @PersistenceContext
    private EntityManager entityManager;

    private final VenueRepository venueRepository;
    private final ModelMapper modelMapper;

    public VenueServiceImpl(VenueRepository venueRepository, ModelMapper modelMapper) {
        this.venueRepository = venueRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<VenueDTO> findAll() {
        return venueRepository.findSorted().stream().map(v -> modelMapper.map(v, VenueDTO.class)).collect(Collectors.toList());
    }

    @Override
    public VenueDTO findById(UUID id) throws NotFoundException {
        return modelMapper.map(venueRepository.findById(id).orElseThrow(NotFoundException::new), VenueDTO.class);
    }

    @Override
    public VenueDTO insert(VenueRequest venueRequest) throws NotFoundException {
        VenueEntity venueEntity = modelMapper.map(venueRequest, VenueEntity.class);
        venueEntity.setVenueId(null);
        venueEntity = venueRepository.saveAndFlush(venueEntity);
        entityManager.refresh(venueEntity);
        return findById(venueEntity.getVenueId());
    }

    @Override
    public List<VenueDTO> findByLocation(UUID locationId) {
        return venueRepository.findByLocation_LocationId(locationId).stream().map(v -> modelMapper.map(v, VenueDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        venueRepository.deleteById(id);
    }

    @Override
    public VenueDTO update(UUID id, VenueRequest venueRequest) throws NotFoundException {
        VenueEntity venueEntity = modelMapper.map(venueRequest, VenueEntity.class);
        venueEntity.setVenueId(id);
        venueEntity = venueRepository.saveAndFlush(venueEntity);
        entityManager.refresh(venueEntity);
        return findById(venueEntity.getVenueId());
    }
}
