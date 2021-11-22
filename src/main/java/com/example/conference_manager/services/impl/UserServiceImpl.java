package com.example.conference_manager.services.impl;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.help.HashClass;
import com.example.conference_manager.models.dto.UserDTO;
import com.example.conference_manager.models.dto.UserGradeDTO;
import com.example.conference_manager.models.request.UserLogin;
import com.example.conference_manager.models.entities.UserEntity;
import com.example.conference_manager.models.request.UserRequest;
import com.example.conference_manager.repositories.UserEntityRepository;
import com.example.conference_manager.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    final UserEntityRepository userEntityRepository;
    final ModelMapper modelMapper;

    public UserServiceImpl(UserEntityRepository userEntityRepository, ModelMapper modelMapper) {
        this.userEntityRepository = userEntityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> findAll() {
        return userEntityRepository.findAll().stream().map(u->modelMapper.map(u, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(UUID id) throws NotFoundException {
        return modelMapper.map(userEntityRepository.findById(id).orElseThrow(NotFoundException::new), UserDTO.class);
    }

    @Override
    public void delete(UUID id) {
        userEntityRepository.deleteById(id);
    }

    @Override
    public UserDTO insert(UserRequest userRequest) throws NotFoundException {
        userRequest.setPassword(HashClass.hashPassword(userRequest.getPassword()));
        UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
        userEntity.setUserId(null);
        userEntity = userEntityRepository.saveAndFlush(userEntity);
        entityManager.refresh(userEntity);
        return findById(userEntity.getUserId());
    }

    @Override
    public UserDTO update(UUID id, UserRequest userRequest) throws NotFoundException {
        UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
        userEntity.setUserId(id);
        userEntity = userEntityRepository.saveAndFlush(userEntity);
        entityManager.refresh(userEntity);
        return findById(userEntity.getUserId());
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return userEntityRepository.existsByUsername(username);
    }

    @Override
    public UserDTO findByUsernameAndPassword(UserLogin userLogin) {
        String temp = HashClass.hashPassword(userLogin.getPassword());
        return modelMapper.map(userEntityRepository.findUserEntityByUsernameAndPassword(userLogin.getUsername(), HashClass.hashPassword(userLogin.getPassword())), UserDTO.class);
    }

    @Override
    public List<UserDTO> findByUserType(String userType) {
        return userEntityRepository.findByUserType(userType).stream().map(u -> modelMapper.map(u, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserGradeDTO> findUsersGradesForConference(UUID userId, UUID conferenceId) {
        return userEntityRepository.findUsersGradesForConference(userId, conferenceId).stream().map(u -> modelMapper.map(u, UserGradeDTO.class)).collect(Collectors.toList());
    }


}
