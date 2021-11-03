package com.example.conference_manager.models.dto;

import com.example.conference_manager.models.entities.EventEntity;
import com.example.conference_manager.models.entities.ResourceEntity;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
public class EventHasResourceDTO {

    private UUID eventResourceId;
    private Integer number;
    //private EventDTO event;
    private ResourceDTO resource;
}
