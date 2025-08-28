package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.services.dtos.responses.AnalysisResponse;

import java.time.LocalDate;

public interface AnalysisService {
    AnalysisResponse analyzeInternPerformance(Integer internId, LocalDate date);
}
