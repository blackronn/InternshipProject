package com.example.InternshipProject.services.dtos.requests;

import lombok.Data;

@Data
public class UpdateEvaluationRequest {
    private int communicationRating;
    private int teamworkRating;
    private String comments;
    private int requestingMentorId;

    private int problemSolvingRating;
    private int responsibilityRating;
    private int timeManagementRating;
}
