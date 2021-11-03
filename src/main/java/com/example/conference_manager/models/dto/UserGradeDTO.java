package com.example.conference_manager.models.dto;
import lombok.Data;

@Data
public class UserGradeDTO {
    private Integer grade;
    private GradingSubjectDTO gradingSubject;
    private UserDTO user;
}
