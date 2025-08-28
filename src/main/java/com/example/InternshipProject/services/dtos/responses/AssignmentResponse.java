package com.example.InternshipProject.services.dtos.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AssignmentResponse {
    private Integer id;
    private String assignmentName;
    private String assignmentDesc;
    private String status;
    private String priority;
    private LocalDate dueDate;
    private LocalDate assignedAt;
    private LocalDate completedAt;
    private LocalDate startedAt;

    private Integer internId;
    private Integer mentorId;
    private String mentorName;
    private String internName;
}