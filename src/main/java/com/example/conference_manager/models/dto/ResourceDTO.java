package com.example.conference_manager.models.dto;

import com.example.conference_manager.models.entities.EventHasResourceEntity;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
public class ResourceDTO {
    private UUID resourceId;
    private String resourceType;
    private String resourceName;
    //private List<EventHasResourceDTO> eventHasResources;
}
