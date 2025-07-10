package com.example.InternshipProject.services.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRelRequest {

    private int relationId;
    private int internId;
    private int mentorId;
}
