package com.example.InternshipProject.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelResponse {
    @JsonProperty("mentor_name")
    private String mentorName;

    @JsonProperty("intern_name")
    private String internName;

    @JsonProperty("mentor_id")
    private int mentorId;

    @JsonProperty("intern_id")
    private int internId;

    @JsonProperty("relation_id")
    private int relationId;

}