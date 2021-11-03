package com.example.conference_manager.models.request;

import lombok.Data;

import java.util.UUID;

@Data
public class EventHasResourceRequest {
    private Integer number;
    private UUID eventId;
    private UUID resourceId;
}
