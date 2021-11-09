package com.example.conference_manager.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ResourceWithNumberDTO {
    private UUID resourceId;
    private String resourceType;
    private String resourceName;
    private Integer number;

    public ResourceWithNumberDTO(UUID resourceId, String resourceType, String resourceName, Integer number) {
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.resourceName = resourceName;
        this.number = number;
    }
}
