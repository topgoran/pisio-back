package com.example.conference_manager.services.impl;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.ResourceDTO;
import com.example.conference_manager.models.dto.ResourceTypeDTO;
import com.example.conference_manager.models.entities.ResourceEntity;
import com.example.conference_manager.models.request.ResourceRequest;
import com.example.conference_manager.repositories.ResourceRepository;
import com.example.conference_manager.services.ResourceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {
    @PersistenceContext
    private EntityManager entityManager;

    private final ModelMapper modelMapper;
    private final ResourceRepository resourceRepository;

    public ResourceServiceImpl(ModelMapper modelMapper, ResourceRepository resourceRepository) {
        this.modelMapper = modelMapper;
        this.resourceRepository = resourceRepository;
    }


    @Override
    public List<ResourceDTO> findAll() {
        return resourceRepository.findAll().stream().map(r -> modelMapper.map(r, ResourceDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ResourceDTO findById(UUID id) throws NotFoundException {
        return modelMapper.map(resourceRepository.findById(id).orElseThrow(NotFoundException::new), ResourceDTO.class);
    }

    @Override
    public ResourceDTO insert(ResourceRequest resourceRequest) throws NotFoundException {
        ResourceEntity resourceEntity = modelMapper.map(resourceRequest, ResourceEntity.class);
        resourceEntity.setResourceId(null);
        resourceEntity = resourceRepository.saveAndFlush(resourceEntity);
        entityManager.refresh(resourceEntity);
        return findById(resourceEntity.getResourceId());
    }

    @Override
    public List<ResourceDTO> findAllByOrdered() {
        return resourceRepository.findAllByOrderByResourceType().stream().map(r -> modelMapper.map(r, ResourceDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        resourceRepository.deleteById(id);
    }

    @Override
    public ResourceDTO update(UUID id, ResourceRequest resourceRequest) throws NotFoundException {
        ResourceEntity resourceEntity = modelMapper.map(resourceRequest, ResourceEntity.class);
        resourceEntity.setResourceId(id);
        resourceEntity = resourceRepository.saveAndFlush(resourceEntity);
        entityManager.refresh(resourceEntity);
        return findById(resourceEntity.getResourceId());
    }

    @Override
    public List<ResourceTypeDTO> findResourceTypes() {
        return resourceRepository.findResourceTypes().stream().map(r -> modelMapper.map(r, ResourceTypeDTO.class)).collect(Collectors.toList());
    }
}
