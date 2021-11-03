package com.example.conference_manager.models.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "location")
public class LocationEntity {
    @Id
    @GeneratedValue
    @Column(name = "location_id")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID locationId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "location")
    private List<ConferenceEntity> conferences;
    @OneToMany(mappedBy = "location")
    private List<VenueEntity> venues;

}
