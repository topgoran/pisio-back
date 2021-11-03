package com.example.conference_manager.models.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "grading_subject_has_user")
public class GradingSubjectHasUserEntity {
    @Id
    @GeneratedValue
    @Column(name = "grade_id")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID gradeId;
    @Basic
    @Column(name = "grade")
    private Integer grade;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "grading_subject_id", nullable = false)
    private GradingSubjectEntity gradingSubject;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity user;

}
