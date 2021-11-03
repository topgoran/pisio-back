package com.example.conference_manager.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LocationDTO {
    private UUID locationId;
    private String name;
    private String address;
}
