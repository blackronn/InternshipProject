package com.example.InternshipProject.dtos.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorDto {
    private Long id;
    private Long internId;// supervisor PK’si
    private Long departmentId;       // FK’si
    private String departmentName;
    private String supervisorName;
    private String supervisorEmail;
}
