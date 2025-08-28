package com.example.InternshipProject.controllers;

import com.example.InternshipProject.services.abstracts.EvaluationService;
import com.example.InternshipProject.services.dtos.requests.CreateEvaluationRequest;
import com.example.InternshipProject.services.dtos.requests.UpdateEvaluationRequest;
import com.example.InternshipProject.services.dtos.responses.EvaluationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@CrossOrigin(origins = "http://localhost:8085")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public ResponseEntity<EvaluationResponse> createEvaluation(@RequestBody CreateEvaluationRequest request) {
        EvaluationResponse response = evaluationService.createEvaluation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/by-mentor-intern")
    public ResponseEntity<EvaluationResponse> getEvaluationByMentorAndIntern(
            @RequestParam int mentorId,
            @RequestParam int internId) {
        EvaluationResponse response = evaluationService.getEvaluationByMentorAndIntern(mentorId, internId);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build(); // Eğer değerlendirme yoksa 404 Not Found döner.
        }
    }

    @GetMapping("/intern/{internId}")
    public ResponseEntity<List<EvaluationResponse>> getEvaluationsByInternId(@PathVariable int internId) {
        List<EvaluationResponse> responses = evaluationService.getEvaluationsByInternId(internId);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{evaluationId}")
    public ResponseEntity<EvaluationResponse> deleteEvaluation(@PathVariable int evaluationId, @RequestParam int requestingMentorId) {
        EvaluationResponse deletedEvaluation = evaluationService.deleteEvaluation(evaluationId, requestingMentorId);
        return ResponseEntity.ok(deletedEvaluation);
    }

    @PutMapping("/{evaluationId}")
    public ResponseEntity<EvaluationResponse> updateEvaluation(@PathVariable int evaluationId, @RequestBody UpdateEvaluationRequest request) {
        EvaluationResponse updatedEvaluation = evaluationService.updateEvaluation(evaluationId, request);
        return ResponseEntity.ok(updatedEvaluation);
    }

}