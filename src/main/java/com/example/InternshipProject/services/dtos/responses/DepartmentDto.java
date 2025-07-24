package com.example.InternshipProject.services.dtos.responses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    @JsonProperty("ID")
    private int ID;

    @JsonProperty("PROGRAM_NAME")
    private String PROGRAM_NAME;
}

