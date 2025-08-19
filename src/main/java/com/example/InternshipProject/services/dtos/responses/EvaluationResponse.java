package com.example.InternshipProject.services.dtos.responses;

import lombok.Data;

@Data
public class EvaluationResponse {
    private Integer id;
    private int communicationRating;
    private int teamworkRating;
    private int problemSolvingRating;
    private int responsibilityRating;
    private int timeManagementRating;
    private String comments;
    private String internName;
    private String mentorName;
}