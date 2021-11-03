package com.example.conference_manager.repositories;

import com.example.conference_manager.models.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<EventEntity, UUID> {
    List<EventEntity> findByUserModerator_UserIdOrderByTimeFromAsc(UUID id);

    @Query("SELECT e FROM EventEntity e WHERE e.venue.venueId = ?1 and ((e.timeFrom between ?2 and ?3) or (e.timeTo between ?2 and ?3 ) or (e.timeFrom < ?2 and e.timeTo > ?3))")
    List<EventEntity> findByVenueVenueIdAndTimeFromBetweenOrTimeToBetween(UUID venueId, Timestamp start, Timestamp end);

    @Query("select distinct e from EventEntity e inner join SessionEntity s on e.session.sessionId = s.sessionId where e.venue.venueId = ?1 and s.date = ?2 order by e.timeFrom ASC ")
    List<EventEntity> findEventsByVenueAndDate(UUID venueId, Timestamp date);
}