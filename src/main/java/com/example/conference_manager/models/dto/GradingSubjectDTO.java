package com.example.conference_manager.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class GradingSubjectDTO {
    private UUID gradingSubjectId;
    private String name;
}
