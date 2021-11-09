package com.example.conference_manager.repositories;

import com.example.conference_manager.models.dto.AverageRatingDTO;
import com.example.conference_manager.models.dto.ConferenceDTO;
import com.example.conference_manager.models.dto.EventIdsDTO;
import com.example.conference_manager.models.entities.ConferenceEntity;
import com.example.conference_manager.models.entities.GradingSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ConferenceRepository extends JpaRepository<ConferenceEntity, UUID> {
    List<ConferenceEntity> findAllByOrderByDateFromAsc();
    List<ConferenceEntity> findByUserCreator_UserIdOrderByDateFromAsc(UUID id);

    @Query("SELECT c.conferenceId FROM ConferenceEntity c inner join SessionEntity s on c.conferenceId = s.conference.conferenceId where s.sessionId = ?1")
    UUID findBySession(UUID id);

    @Query("SELECT distinct c FROM ConferenceEntity c inner join SessionEntity s on c.conferenceId = s.conference.conferenceId inner join EventEntity e on s.sessionId = e.session.sessionId where e.eventId in ?1")
    List<ConferenceEntity> findByEventIds(List<UUID> eventId);

    @Query("select c from ConferenceEntity c where c.dateTo > CURRENT_TIMESTAMP order by c.dateFrom asc ")
    List<ConferenceEntity> findNew();

    @Query("select new com.example.conference_manager.models.dto.AverageRatingDTO(gs.name, avg(gshu.grade)) from ConferenceEntity c inner join GradingSubjectEntity gs on c.conferenceId = gs.conference.conferenceId inner join GradingSubjectHasUserEntity gshu on gs.gradingSubjectId = gshu.gradingSubject.gradingSubjectId where c.conferenceId = ?1 group by gs.gradingSubjectId")
    List<AverageRatingDTO> findAverageRatings(UUID conferenceId);

    @Query("select gs from ConferenceEntity c inner join GradingSubjectEntity gs on c.conferenceId = gs.conference.conferenceId where c.conferenceId = ?1")
    List<GradingSubjectEntity> findGradingSubjectsByConferenceId(UUID id);
}
