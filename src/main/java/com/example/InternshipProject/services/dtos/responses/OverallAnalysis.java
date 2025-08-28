package com.example.InternshipProject.services.dtos.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OverallAnalysis {

    @JsonProperty("developmentScore")
    private int developmentScore;

    @JsonProperty("motivationScore")
    private int motivationScore;

    @JsonProperty("motivationStatus")
    private String motivationStatus;

    @JsonProperty("riskLevel")
    private String riskLevel;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("keyTopics")
    private Object keyTopics;
}