package com.example.conference_manager.repositories;

import com.example.conference_manager.models.entities.EventHasAttendeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EventHasAttendeeRepository extends JpaRepository<EventHasAttendeeEntity, UUID> {
    Integer countAllByEventEventId(UUID id);
    EventHasAttendeeEntity findByUserUserIdAndEventEventId(UUID userId, UUID eventId);

    @Query("SELECT e.event.eventId FROM EventHasAttendeeEntity e where e.user.userId = ?1")
    List<UUID> findByUserUserId(UUID attendeeId);
}
