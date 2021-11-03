package com.example.conference_manager.services.impl;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.LocationDTO;
import com.example.conference_manager.models.dto.LocationWithVenuesDTO;
import com.example.conference_manager.models.entities.LocationEntity;
import com.example.conference_manager.models.request.LocationRequest;
import com.example.conference_manager.repositories.LocationRepository;
import com.example.conference_manager.services.LocationService;
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
public class LocationServiceImpl implements LocationService {

    @PersistenceContext
    EntityManager entityManager;

    final
    LocationRepository locationRepository;
    final
    ModelMapper modelMapper;

    public LocationServiceImpl(LocationRepository locationRepository, ModelMapper modelMapper) {
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LocationDTO> findAll() {
        return locationRepository.findAll().stream().map(l->modelMapper.map(l, LocationDTO.class)).collect(Collectors.toList());
    }

    @Override
    public LocationDTO findById(UUID id) throws NotFoundException {
        return modelMapper.map(locationRepository.findById(id).orElseThrow(NotFoundException::new), LocationDTO.class);
    }

    @Override
    public LocationWithVenuesDTO findByIdWithVenues(UUID id) throws NotFoundException {
        return modelMapper.map(locationRepository.findById(id).orElseThrow(NotFoundException::new), LocationWithVenuesDTO.class);
    }

    @Override
    public LocationDTO insert(LocationRequest locationRequest) throws NotFoundException {
        LocationEntity locationEntity = modelMapper.map(locationRequest, LocationEntity.class);
        locationEntity.setLocationId(null);
        locationEntity = locationRepository.saveAndFlush(locationEntity);
        entityManager.refresh(locationEntity);
        return findById(locationEntity.getLocationId());
    }

    @Override
    public void delete(UUID id) {
        locationRepository.deleteById(id);
    }

    @Override
    public LocationDTO update(UUID id, LocationRequest locationRequest) throws NotFoundException {
        LocationEntity locationEntity = modelMapper.map(locationRequest, LocationEntity.class);
        locationEntity.setLocationId(id);
        locationEntity = locationRepository.saveAndFlush(locationEntity);
        entityManager.refresh(locationEntity);
        return findById(locationEntity.getLocationId());
    }
}
