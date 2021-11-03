package com.example.conference_manager.models.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class VenueDTO {
    private UUID venueId;
    private String name;
    private Integer numberOfPlaces;
    private String locationName;
}
