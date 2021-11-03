package com.example.conference_manager.models.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "resource")
public class ResourceEntity {
    @Id
    @GeneratedValue
    @Column(name = "resource_id")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID resourceId;
    @Basic
    @Column(name = "resource_type")
    private String resourceType;
    @Basic
    @Column(name = "resource_name")
    private String resourceName;
    @OneToMany(mappedBy = "resource")
    private List<EventHasResourceEntity> eventHasResources;

}
