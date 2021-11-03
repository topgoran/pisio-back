package com.example.conference_manager.models.request;

import lombok.Data;

import java.util.UUID;

@Data
public class VenueRequest {
    private String name;
    private Integer numberOfPlaces;
    private UUID locationId;
}
