package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long> {

    List<AnalysisResult> findByInternIdOrderByAnalysisDateDesc(Integer internId);
}