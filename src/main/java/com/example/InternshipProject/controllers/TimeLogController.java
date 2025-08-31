package com.example.InternshipProject.controllers;

import com.example.InternshipProject.services.abstracts.TimeLogService;
import com.example.InternshipProject.services.dtos.requests.CreateTimeLogRequest;
import com.example.InternshipProject.services.dtos.responses.TimeLogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/timelogs")
@RequiredArgsConstructor
@CrossOrigin
public class TimeLogController {

    private final TimeLogService timeLogService;

    @PostMapping
    public ResponseEntity<TimeLogResponse> add(@RequestBody CreateTimeLogRequest request) {
        return ResponseEntity.ok(timeLogService.add(request));
    }

    @GetMapping
    public ResponseEntity<List<TimeLogResponse>> getAll() {
        return ResponseEntity.ok(timeLogService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeLogResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(timeLogService.getById(id));
    }

    @GetMapping("/intern/{internId}")
    public ResponseEntity<List<TimeLogResponse>> getByInternId(@PathVariable Integer internId) {
        return ResponseEntity.ok(timeLogService.getByInternId(internId));
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<TimeLogResponse>> getByAssignmentId(@PathVariable Integer assignmentId) {
        return ResponseEntity.ok(timeLogService.getByAssignmentId(assignmentId));
    }

    @GetMapping("/intern/{internId}/date-range")
    public ResponseEntity<List<TimeLogResponse>> getByInternIdAndDateRange(
            @PathVariable Integer internId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(timeLogService.getByInternIdAndDateRange(internId, startDate, endDate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        timeLogService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeLogResponse> update(
            @PathVariable Integer id,
            @RequestBody CreateTimeLogRequest request) {
        return ResponseEntity.ok(timeLogService.update(id, request));
    }
}
