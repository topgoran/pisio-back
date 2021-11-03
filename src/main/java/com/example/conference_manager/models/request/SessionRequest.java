package com.example.conference_manager.models.request;

import com.example.conference_manager.models.entities.UserEntity;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class SessionRequest {
    private String name;
    private String description;
    private Timestamp date;
    private UUID conferenceId;
    private UUID userId;
}
