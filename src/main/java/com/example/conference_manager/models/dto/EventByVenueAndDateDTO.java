package com.example.conference_manager.models.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class EventByVenueAndDateDTO {
    private UUID venueId;
    private Timestamp date;
}
