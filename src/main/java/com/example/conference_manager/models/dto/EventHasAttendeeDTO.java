package com.example.conference_manager.models.dto;

import com.example.conference_manager.models.entities.EventEntity;
import com.example.conference_manager.models.entities.UserEntity;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
public class EventHasAttendeeDTO {
    private UUID eventHasAttendee;
    private UUID eventId;
    private UserDTO user;
}
