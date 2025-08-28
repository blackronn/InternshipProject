package com.example.InternshipProject.controllers;

// Gerekli import'ları ekle
import com.example.InternshipProject.entities.concretes.AnalysisResult;
import com.example.InternshipProject.repositories.AnalysisResultRepository;
import com.example.InternshipProject.services.abstracts.AnalysisService;
import com.example.InternshipProject.services.dtos.responses.AnalysisResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final AnalysisService analysisService;
    private final AnalysisResultRepository analysisResultRepository; // 1. ALAN OLARAK EKLE

    // 2. CONSTRUCTOR'I GÜNCELLE
    public AnalysisController(AnalysisService analysisService, AnalysisResultRepository analysisResultRepository) {
        this.analysisService = analysisService;
        this.analysisResultRepository = analysisResultRepository; // Atamasını yap
    }

    @GetMapping("/intern/{internId}")
    public ResponseEntity<AnalysisResponse> getAnalysisForIntern(
            @PathVariable Integer internId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        if (date == null) {
            date = LocalDate.now();
        }

        AnalysisResponse result = analysisService.analyzeInternPerformance(internId, date);
        return ResponseEntity.ok(result);
    }

    // YENİ EKLEDİĞİN ENDPOINT ARTIK HATASIZ ÇALIŞACAK
    @GetMapping("/intern/{internId}/history")
    public ResponseEntity<List<AnalysisResult>> getAnalysisHistoryForIntern(@PathVariable Integer internId) {
        List<AnalysisResult> history = analysisResultRepository.findByInternIdOrderByAnalysisDateDesc(internId);
        return ResponseEntity.ok(history);

    }
}