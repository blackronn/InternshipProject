package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.services.dtos.requests.CreateAnnouncementRequest;
import com.example.InternshipProject.services.dtos.responses.AnnouncementResponse;

import java.util.List;

public interface AnnouncementService {
    void createAnnouncement(CreateAnnouncementRequest request);
    List<AnnouncementResponse> getRecentAnnouncements(String internEmail);
}
