package com.example.conference_manager.repositories;

import com.example.conference_manager.models.entities.EventTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventTypeRepository extends JpaRepository<EventTypeEntity, UUID> {
}
