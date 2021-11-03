package com.example.conference_manager.models.request;

import com.example.conference_manager.models.entities.EventTypeEntity;
import com.example.conference_manager.models.entities.SessionEntity;
import com.example.conference_manager.models.entities.UserEntity;
import com.example.conference_manager.models.entities.VenueEntity;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class EventRequest {
    private String name;
    private String description;
    private Timestamp timeFrom;
    private Timestamp timeTo;
    private Boolean isOnline;
    private String accessLink;
    private String accessPassword;
    private UUID sessionId;
    private UUID eventTypeId;
    private UUID venueId;
    private UUID userLecturerId;
    private UUID userModeratorId;
}
