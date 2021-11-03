package com.example.conference_manager.models.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "venue")
public class VenueEntity {
    @Id
    @GeneratedValue
    @Column(name = "venue_id")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID venueId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "number_of_places")
    private Integer numberOfPlaces;
    @OneToMany(mappedBy = "venue")
    private List<EventEntity> events;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    private LocationEntity location;

}
