package com.example.conference_manager.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class EventTypeDTO {
    private UUID id;
    private String eventTypeName;
}
