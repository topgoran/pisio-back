package com.example.conference_manager.repositories;

import com.example.conference_manager.models.dto.LocationWithVenuesDTO;
import com.example.conference_manager.models.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<LocationEntity, UUID> {

}
