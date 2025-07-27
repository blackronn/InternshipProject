package com.example.InternshipProject.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AnnouncementResponse {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long mentorId;
}
