package com.example.conference_manager.models.request;

import com.example.conference_manager.models.entities.GradingSubjectEntity;
import com.example.conference_manager.models.entities.UserEntity;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
public class UserGradeRequest {
    private Integer grade;
    private UUID gradingSubjectId;
    private UUID userId;
}
