package com.example.conference_manager.models.request;

import com.example.conference_manager.models.entities.EventEntity;
import com.example.conference_manager.models.entities.UserEntity;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
public class EventHasAttendeeRequest {
    private UUID eventId;
    private UUID userId;
}
