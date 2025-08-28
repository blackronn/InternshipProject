package com.example.InternshipProject.services.dtos.responses;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TimeLogResponse {
    private Integer id;
    private Double spentTimeInHours; // Saat cinsinden (1.5 = 1.5 saat)
    private LocalDateTime logDate;
    private Integer assignmentId;
    private String assignmentName;
    private Integer internId;
    private String internName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
