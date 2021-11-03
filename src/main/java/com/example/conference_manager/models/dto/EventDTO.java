package com.example.conference_manager.models.dto;

import com.example.conference_manager.models.entities.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
public class EventDTO {
    private UUID eventId;
    private String name;
    private String description;
    private Timestamp timeFrom;
    private Timestamp timeTo;
    private Boolean isOnline;
    private String accessLink;
    private String accessPassword;
    private EventTypeDTO eventType;
    private VenueDTO venue;
    private UserDTO userLecturer;
    private UserDTO userModerator;
    private List<EventHasAttendeeDTO> eventHasAttendees;
    private List<EventHasResourceDTO> eventHasResources;
}
