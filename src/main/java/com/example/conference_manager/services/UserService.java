package com.example.conference_manager.services;

import com.example.conference_manager.exceptions.NotFoundException;
import com.example.conference_manager.models.dto.UserDTO;
import com.example.conference_manager.models.dto.UserGradeDTO;
import com.example.conference_manager.models.request.UserLogin;
import com.example.conference_manager.models.request.UserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findById(UUID id) throws NotFoundException;
    void delete(UUID id);
    UserDTO insert(UserRequest userRequest) throws NotFoundException;
    UserDTO update(UUID id, UserRequest userRequest) throws NotFoundException;
    boolean isUsernameUnique(String username);
    UserDTO findByUsernameAndPassword(UserLogin userLogin);
    List<UserDTO> findByUserType(String userType);

    List<UserGradeDTO> findUsersGradesForConference(UUID userId, UUID conferenceId);
}
