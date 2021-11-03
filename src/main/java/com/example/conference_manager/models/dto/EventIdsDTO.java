package com.example.conference_manager.models.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EventIdsDTO {
    private List<UUID> eventIds;
}
