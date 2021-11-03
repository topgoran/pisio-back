package com.example.conference_manager.models.request;


import lombok.Data;

import java.util.UUID;

@Data
public class GradingSubjectRequest {
    private String name;
    private UUID conferenceId;
}
