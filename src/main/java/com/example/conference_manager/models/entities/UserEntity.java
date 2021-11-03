package com.example.conference_manager.models.entities;

import com.example.conference_manager.models.dto.ConferenceDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID userId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "username", unique = true)
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "user_type")
    private String userType;
    @OneToMany(mappedBy = "userCreator")
    private List<ConferenceEntity> conferencesCreator;
    @OneToMany(mappedBy = "userLecturer")
    private List<EventEntity> eventsLecturer;
    @OneToMany(mappedBy = "userModerator")
    private List<EventEntity> eventsModerator;
    @OneToMany(mappedBy = "user")
    private List<EventHasAttendeeEntity> eventHasAttendees;
    @OneToMany(mappedBy = "user")
    private List<GradingSubjectHasUserEntity> gradingSubjectHasUsers;
    @OneToMany(mappedBy = "user")
    private List<SessionEntity> sessions;

}
