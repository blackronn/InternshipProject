package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.TimeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeLogRepository extends JpaRepository<TimeLog, Integer> {
    List<TimeLog> findByInternId(Integer internId);
    List<TimeLog> findByAssignmentId(Integer assignmentId);
    List<TimeLog> findByInternIdAndLogDateBetween(Integer internId, LocalDateTime startDate, LocalDateTime endDate);
}
