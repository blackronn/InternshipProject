package com.example.InternshipProject.controllers;

import com.example.InternshipProject.repositories.AssignmentRepository;
import com.example.InternshipProject.services.abstracts.AssignmentService;
import com.example.InternshipProject.services.dtos.requests.CreateAssignmentRequest;
import com.example.InternshipProject.services.dtos.responses.AssignmentResponse;
import com.example.InternshipProject.entities.concretes.Assignment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final AssignmentRepository assignmentRepository;

    @PostMapping
    public AssignmentResponse add(@RequestBody CreateAssignmentRequest request) {
        return assignmentService.add(request);
    }

    @GetMapping
    public List<AssignmentResponse> getAll() {
        return assignmentService.getAll();
    }

    @GetMapping("/{id}")
    public AssignmentResponse getById(@PathVariable int id) {
        return assignmentService.getById(id);
    }

    @PutMapping("/{id}")
    public AssignmentResponse update(@PathVariable int id, @RequestBody Assignment assignment) {
        return assignmentService.update(id, assignment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        assignmentService.delete(id);
    }

    @GetMapping("/stats")
    public Map<String, Map<String, Long>> getAssignmentStats() {
        List<Assignment> assignments = assignmentRepository.findAll();

        Map<String, Map<String, Long>> result = new HashMap<>();

        for (Assignment assignment : assignments) {
            if (assignment == null) continue;

            if (assignment.getIntern() == null || assignment.getStatus() == null) {
                continue;
            }

            String email = assignment.getIntern().getEmail();
            String status = assignment.getStatus().equalsIgnoreCase("completed") ? "Yapıldı" : "Yapılmadı";

            result.putIfAbsent(email, new HashMap<>());
            Map<String, Long> userStats = result.get(email);
            userStats.put(status, userStats.getOrDefault(status, 0L) + 1);
        }

        return result;
    }


    @GetMapping("{internId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsByInternId(@PathVariable Integer internId){
        List<AssignmentResponse> assignments=assignmentService.findAssignmentsByInternId(internId);
        return ResponseEntity.ok(assignments);
    }
    @GetMapping("/by-mentor/{mentorId}")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsForMentorInterns(@PathVariable Integer mentorId) {
        List<AssignmentResponse> assignments = assignmentService.findAssignmentsByMentorId(mentorId);
        return ResponseEntity.ok(assignments);
    }
}

