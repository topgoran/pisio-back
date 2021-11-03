package com.example.conference_manager.models.request;

import com.example.conference_manager.models.dto.LocationDTO;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ConferenceRequest {
    private String name;
    private String description;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private Boolean isOnline;
    private String imageUrl;
    private UUID locationId;
    private UUID userCreatorId;
}
