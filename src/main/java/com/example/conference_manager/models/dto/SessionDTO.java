package com.example.conference_manager.models.dto;

import com.example.conference_manager.models.entities.ConferenceEntity;
import com.example.conference_manager.models.entities.EventEntity;
import com.example.conference_manager.models.entities.UserEntity;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
public class SessionDTO {
    private UUID sessionId;
    private String name;
    private String description;
    private Timestamp date;
    private List<EventDTO> events;
    private UserDTO user;
}
