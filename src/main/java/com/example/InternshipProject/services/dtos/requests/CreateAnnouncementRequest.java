package com.example.InternshipProject.services.dtos.requests;

import lombok.Data;

@Data
public class CreateAnnouncementRequest {
    private String title;
    private String content;
}
