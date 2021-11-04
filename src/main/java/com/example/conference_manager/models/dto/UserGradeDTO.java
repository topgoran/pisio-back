package com.example.conference_manager.models.dto;
import lombok.Data;

import java.util.UUID;

@Data
public class UserGradeDTO {
    private UUID gradeId;
    private Integer grade;
    private GradingSubjectDTO gradingSubject;
    private UserDTO user;
}
