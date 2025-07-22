package com.example.InternshipProject.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorResponse {
    private String name;
    private String surname;
    private String email;
}
