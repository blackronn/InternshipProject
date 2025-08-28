package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Assignment;
import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.entities.concretes.TimeLog;
import com.example.InternshipProject.repositories.AssignmentRepository;
import com.example.InternshipProject.repositories.InternRepository;
import com.example.InternshipProject.repositories.TimeLogRepository;
import com.example.InternshipProject.services.abstracts.TimeLogService;
import com.example.InternshipProject.services.dtos.requests.CreateTimeLogRequest;
import com.example.InternshipProject.services.dtos.responses.TimeLogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TimeLogServiceImpl implements TimeLogService {

    private final TimeLogRepository timeLogRepository;
    private final InternRepository internRepository;
    private final AssignmentRepository assignmentRepository;


    @Override
    public TimeLogResponse add(CreateTimeLogRequest request) {
        TimeLog timeLog = new TimeLog();

        // Önce stajyerin var olup olmadığını kontrol eder
        Intern intern = internRepository.findById(request.getInternId())
                .orElseThrow(() -> new RuntimeException("Stajyer bulunamadı"));

        // Sonra görevin var olup olmadığını kontrol eder
        Assignment assignment = assignmentRepository.findById((long) request.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Görev bulunamadı"));

        // Yeni zaman kaydını oluşturur
        timeLog.setIntern(intern);
        timeLog.setAssignment(assignment);
        timeLog.setLogDate(request.getLogDate());
        timeLog.setSpentTimeInHours(request.getSpentTimeInHours());

        TimeLog saved = timeLogRepository.save(timeLog);
        return convertToResponse(saved);
    }

    @Override
    public List<TimeLogResponse> getAll() {
        return timeLogRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TimeLogResponse getById(Integer id) {
        TimeLog timeLog = timeLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log kaydı bulunamadı"));
        return convertToResponse(timeLog);
    }

    @Override
    public List<TimeLogResponse> getByInternId(Integer internId) {
        return timeLogRepository.findByInternId(internId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeLogResponse> getByAssignmentId(Integer assignmentId) {
        return timeLogRepository.findByAssignmentId(assignmentId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeLogResponse> getByInternIdAndDateRange(Integer internId, LocalDateTime startDate, LocalDateTime endDate) {
        return timeLogRepository.findByInternIdAndLogDateBetween(internId, startDate, endDate).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        timeLogRepository.deleteById(id);
    }

    @Override
    public TimeLogResponse update(Integer id, CreateTimeLogRequest request) {
        TimeLog timeLog = timeLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log kaydı bulunamadı"));

        Intern intern = internRepository.findById(request.getInternId())
                .orElseThrow(() -> new RuntimeException("Stajyer bulunamadı"));

        Assignment assignment = assignmentRepository.findById((long) request.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Görev bulunamadı"));

        timeLog.setIntern(intern);
        timeLog.setAssignment(assignment);
        timeLog.setLogDate(request.getLogDate());
        timeLog.setSpentTimeInHours(request.getSpentTimeInHours());

        TimeLog updated = timeLogRepository.save(timeLog);
        return convertToResponse(updated);
    }

    // TimeLog verisini API yanıtına dönüştürür
    private TimeLogResponse convertToResponse(TimeLog timeLog) {
        TimeLogResponse response = new TimeLogResponse();
        response.setId(timeLog.getId());
        response.setSpentTimeInHours(timeLog.getSpentTimeInHours());
        response.setLogDate(timeLog.getLogDate());
        response.setAssignmentId(timeLog.getAssignment().getId());
        response.setAssignmentName(timeLog.getAssignment().getAssignmentName());
        response.setInternId(timeLog.getIntern().getId());
        response.setInternName(timeLog.getIntern().getName() + " " + timeLog.getIntern().getSurname());
        response.setCreatedAt(timeLog.getCreatedAt());
        response.setUpdatedAt(timeLog.getUpdatedAt());
        return response;
    }
}