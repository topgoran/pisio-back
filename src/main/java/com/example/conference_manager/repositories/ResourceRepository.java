package com.example.conference_manager.repositories;

import com.example.conference_manager.models.entities.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ResourceRepository extends JpaRepository<ResourceEntity, UUID> {
    List<ResourceEntity> findAllByOrderByResourceType();

    @Query("select r from ResourceEntity r group by r.resourceType")
    List<ResourceEntity> findResourceTypes();
}
