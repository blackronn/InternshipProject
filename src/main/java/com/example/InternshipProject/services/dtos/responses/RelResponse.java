package com.example.InternshipProject.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelResponse {
    @JsonProperty("mentor_name")
    private String mentorName;

    @JsonProperty("intern_name")
    private String internName;

    @JsonProperty("relation_id")
    private Integer relationId;

    @JsonProperty("mentor_id")
    private Integer mentorId;

    @JsonProperty("intern_id")
    private Integer internId;

    @JsonProperty("is_active")
    private Integer isActive;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;


}