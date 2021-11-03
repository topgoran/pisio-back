package com.example.conference_manager.repositories;

import com.example.conference_manager.models.entities.GradingSubjectHasUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserGradeRepository extends JpaRepository<GradingSubjectHasUserEntity, UUID> {
    GradingSubjectHasUserEntity findByUserUserIdAndGradingSubjectGradingSubjectId(UUID userId, UUID subjectId);
}
