package com.example.InternshipProject.entities.concretes;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "analysis_results")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AnalysisResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "intern_id", nullable = false)
    private Intern intern;

    @Column(name = "analysis_date", nullable = false)
    private LocalDate analysisDate;

    @Column(name = "development_score")
    private int developmentScore;

    @Column(name = "motivation_score")
    private int motivationScore;

    @Column(name = "motivation_status")
    private String motivationStatus;

    @Column(name = "risk_level")
    private String riskLevel;

    @Column(name = "summary", length = 2000)
    private String summary;
}