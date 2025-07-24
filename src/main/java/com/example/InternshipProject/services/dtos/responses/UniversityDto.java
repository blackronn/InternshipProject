package com.example.InternshipProject.services.dtos.responses;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class UniversityDto {
    @JsonProperty("name")
    private String name;
}
