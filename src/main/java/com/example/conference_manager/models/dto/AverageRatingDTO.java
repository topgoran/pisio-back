package com.example.conference_manager.models.dto;

import lombok.Data;

@Data
public class AverageRatingDTO {
    private String gradingSubjectName;
    private Double avgRating;

    public AverageRatingDTO(String gradingSubjectName, Double avgRating){
        this.gradingSubjectName = gradingSubjectName;
        this.avgRating = avgRating;
    }
}
