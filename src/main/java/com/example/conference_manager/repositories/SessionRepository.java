package com.example.conference_manager.repositories;

import com.example.conference_manager.models.dto.ConferenceDTO;
import com.example.conference_manager.models.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<SessionEntity, UUID> {
    List<SessionEntity> findByConference_ConferenceIdOrderByDateAsc(UUID conferenceId);
    List<SessionEntity> findByUser_UserIdOrderByDateAsc(UUID id);
    List<SessionEntity> findAllByOrderByDateAsc();

    @Query("SELECT s.sessionId FROM SessionEntity s inner join EventEntity e on s.sessionId = e.session.sessionId where e.eventId = ?1")
    UUID findByEvent(UUID id);
}
