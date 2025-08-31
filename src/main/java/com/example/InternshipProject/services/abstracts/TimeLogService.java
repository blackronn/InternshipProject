package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.services.dtos.requests.CreateTimeLogRequest;
import com.example.InternshipProject.services.dtos.responses.TimeLogResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeLogService {
    TimeLogResponse add(CreateTimeLogRequest request);
    List<TimeLogResponse> getAll();
    TimeLogResponse getById(Integer id);
    List<TimeLogResponse> getByInternId(Integer internId);
    List<TimeLogResponse> getByAssignmentId(Integer assignmentId);
    List<TimeLogResponse> getByInternIdAndDateRange(Integer internId, LocalDateTime startDate, LocalDateTime endDate);
    void delete(Integer id);
    TimeLogResponse update(Integer id, CreateTimeLogRequest request);
}
