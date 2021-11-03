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
@Table(name = "conference")
public class ConferenceEntity {
    @Id
    @GeneratedValue
    @Column(name = "conference_id")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID conferenceId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "date_from")
    private Timestamp dateFrom;
    @Basic
    @Column(name = "date_to")
    private Timestamp dateTo;
    private Boolean isOnline;
    @Basic
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private LocationEntity location;
    @OneToMany(mappedBy = "conference")
    private List<GradingSubjectEntity> gradingSubjects;
    @OneToMany(mappedBy = "conference")
    @OrderBy("date asc")
    private List<SessionEntity> sessions;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userCreator;

    @Basic
    @Column(name = "is_online")
    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

}
