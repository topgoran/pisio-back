package com.example.conference_manager.models.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "grading_subject")
public class GradingSubjectEntity {
    @Id
    @GeneratedValue
    @Column(name = "grading_subject_id")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID gradingSubjectId;
    @Basic
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conference_id", referencedColumnName = "conference_id", nullable = false)
    private ConferenceEntity conference;
    @OneToMany(mappedBy = "gradingSubject")
    private List<GradingSubjectHasUserEntity> gradingSubjectHasUsers;

}
