package com.example.conference_manager.models.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "event_has_resource")
public class EventHasResourceEntity {
    @Id
    @GeneratedValue
    @Column(name = "event_resource_id")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID eventResourceId;
    @Basic
    @Column(name = "number")
    private Integer number;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "event_id", nullable = false)
    private EventEntity event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", referencedColumnName = "resource_id", nullable = false)
    private ResourceEntity resource;

}
