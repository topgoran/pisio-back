package com.example.conference_manager.models.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "event")
public class EventEntity {
    @Id
    @GeneratedValue
    @Column(name = "event_id")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID eventId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "time_from")
    private Timestamp timeFrom;
    @Basic
    @Column(name = "time_to")
    private Timestamp timeTo;
    private Boolean isOnline;
    @Basic
    @Column(name = "access_link")
    private String accessLink;
    @Basic
    @Column(name = "access_password")
    private String accessPassword;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", referencedColumnName = "session_id", nullable = false)
    private SessionEntity session;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_id", referencedColumnName = "event_type_id", nullable = false)
    private EventTypeEntity eventType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", referencedColumnName = "venue_id")
    private VenueEntity venue;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userLecturer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userModerator;
    @OneToMany(mappedBy = "event")
    private List<EventHasAttendeeEntity> eventHasAttendees;
    @OneToMany(mappedBy = "event")
    private List<EventHasResourceEntity> eventHasResources;

    @Basic
    @Column(name = "is_online")
    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

}
