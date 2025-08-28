package com.example.InternshipProject.services.dtos.requests;

import lombok.Data;

@Data
public class CreateEvaluationRequest {
    private int internId;
    private int mentorId;
    private int communicationRating;
    private int teamworkRating;
    private String comments;

    private int problemSolvingRating;
    private int responsibilityRating;
    private int timeManagementRating;
}