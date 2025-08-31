package com.example.InternshipProject.services.dtos.requests;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateTimeLogRequest {
    private Double spentTimeInHours; // Saat cinsinden (1.5 = 1.5 saat)
    private LocalDateTime logDate;
    private Integer assignmentId;
    private Integer internId;
}
