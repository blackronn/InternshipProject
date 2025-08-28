package com.example.InternshipProject.services.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalysisResponse {

    @JsonProperty("internId") // Python'dan gelen 'internId' bu alana gelecek
    private int internId;

    @JsonProperty("overallAnalysis") // Python'dan gelen 'overallAnalysis' bu alana gelecek
    private OverallAnalysis overallAnalysis;
}