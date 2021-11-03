package com.example.conference_manager.models.dto;

import com.example.conference_manager.models.entities.GradingSubjectEntity;
import com.example.conference_manager.models.entities.LocationEntity;
import com.example.conference_manager.models.entities.SessionEntity;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
public class ConferenceDTO {
    private UUID conferenceId;
    private String name;
    private String description;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private Boolean isOnline;
    private String imageUrl;
    private LocationDTO location;
    private List<GradingSubjectDTO> gradingSubjects;
    private List<SessionDTO> sessions;
    private UserDTO userCreator;
}
