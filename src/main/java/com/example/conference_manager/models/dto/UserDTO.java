package com.example.conference_manager.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String name;
    private String lastName;
    private String username;
    private String userType;
}
