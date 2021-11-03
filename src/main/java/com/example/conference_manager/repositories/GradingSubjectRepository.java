package com.example.conference_manager.repositories;

import com.example.conference_manager.models.entities.GradingSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GradingSubjectRepository extends JpaRepository<GradingSubjectEntity, UUID> {
}
