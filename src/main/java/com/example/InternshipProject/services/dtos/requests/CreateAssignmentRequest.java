package com.example.InternshipProject.services.dtos.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAssignmentRequest {
    private String assignmentName;
    private String assignmentDesc;
    private String status;
    private String priority;
    private LocalDate dueDate;
    private LocalDate assignedAt;
    private LocalDate completedAt;
    private int internId;
    private int mentorId;
    private LocalDate startedAt;
}