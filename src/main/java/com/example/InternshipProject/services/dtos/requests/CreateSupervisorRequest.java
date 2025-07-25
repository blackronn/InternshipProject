package com.example.InternshipProject.dtos.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSupervisorRequest {
    private Long internId;
    private Long supervisorDepartmentId;
    private String supervisorName;
    private String supervisorEmail;
}
