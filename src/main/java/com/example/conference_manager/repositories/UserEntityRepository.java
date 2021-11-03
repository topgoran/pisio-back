package com.example.conference_manager.repositories;

import com.example.conference_manager.models.dto.UserDTO;
import com.example.conference_manager.models.dto.UserGradeDTO;
import com.example.conference_manager.models.entities.GradingSubjectHasUserEntity;
import com.example.conference_manager.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByUsername(String username);
    UserEntity findUserEntityByUsernameAndPassword(String username, String password);
    List<UserEntity> findByUserType(String userType);

    @Query("select g from UserEntity u inner join GradingSubjectHasUserEntity g on u.userId = g.user.userId inner join GradingSubjectEntity gs on g.gradingSubject.gradingSubjectId = gs.gradingSubjectId inner join ConferenceEntity c on c.conferenceId = gs.conference.conferenceId where u.userId = ?1 and c.conferenceId = ?2")
    List<GradingSubjectHasUserEntity> findUsersGradesForConference(UUID userId, UUID conferenceId);
}
