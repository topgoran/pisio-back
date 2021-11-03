package com.example.conference_manager.models.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "event_type")
public class EventTypeEntity {
    @Id
    @GeneratedValue
    @Column(name = "event_type_id")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID eventTypeId;
    @Basic
    @Column(name = "event_type_name")
    private String eventTypeName;
    @OneToMany(mappedBy = "eventType")
    private List<EventEntity> events;

}
