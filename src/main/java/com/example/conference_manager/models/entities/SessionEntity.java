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
@Table(name = "session")
public class SessionEntity {
    @Id
    @GeneratedValue
    @Column(name = "session_id")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID sessionId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "date")
    private Timestamp date;
    @OneToMany(mappedBy = "session")
    @OrderBy("timeFrom asc")
    private List<EventEntity> events;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conference_id", referencedColumnName = "conference_id", nullable = false)
    private ConferenceEntity conference;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity user;

}
