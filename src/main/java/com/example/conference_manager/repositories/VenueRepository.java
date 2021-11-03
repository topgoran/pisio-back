package com.example.conference_manager.repositories;

import com.example.conference_manager.models.dto.VenueDTO;
import com.example.conference_manager.models.entities.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VenueRepository extends JpaRepository<VenueEntity, UUID> {
    List<VenueEntity> findByLocation_LocationId(UUID locationId);

    @Query("select v from VenueEntity v order by v.location.locationId, v.name")
    List<VenueEntity> findSorted();
}
