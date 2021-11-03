package com.example.conference_manager.models.dto;

import com.example.conference_manager.models.entities.VenueEntity;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class LocationWithVenuesDTO {
    private UUID locationId;
    private String name;
    private String address;
    private List<VenueDTO> venues;
}
