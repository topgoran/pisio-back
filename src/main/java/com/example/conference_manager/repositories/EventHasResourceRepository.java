package com.example.conference_manager.repositories;

import com.example.conference_manager.models.entities.EventHasResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventHasResourceRepository extends JpaRepository<EventHasResourceEntity, UUID> {
}
