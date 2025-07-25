package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByCreatedAtAfter(LocalDateTime after);
}
