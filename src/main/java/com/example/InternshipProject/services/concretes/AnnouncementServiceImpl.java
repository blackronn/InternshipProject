package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Announcement;
import com.example.InternshipProject.repositories.AnnouncementRepository;
import com.example.InternshipProject.services.abstracts.AnnouncementService;
import com.example.InternshipProject.services.dtos.requests.CreateAnnouncementRequest;
import com.example.InternshipProject.services.dtos.responses.AnnouncementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public void createAnnouncement(CreateAnnouncementRequest request) {
        Announcement announcement = new Announcement();
        announcement.setTitle(request.getTitle());
        announcement.setContent(request.getContent());
        announcement.setCreatedAt(LocalDateTime.now());
        announcementRepository.save(announcement);
    }

    @Override
    public List<AnnouncementResponse> getRecentAnnouncements() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusDays(7);
        return announcementRepository.findByCreatedAtAfter(oneWeekAgo)
                .stream()
                .map(a -> new AnnouncementResponse(
                        a.getTitle(),
                        a.getContent(),
                        a.getCreatedAt()
                ))
                .toList();
    }
}
