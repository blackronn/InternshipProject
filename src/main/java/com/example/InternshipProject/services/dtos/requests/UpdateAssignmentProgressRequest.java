package com.example.InternshipProject.services.dtos.requests;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UpdateAssignmentProgressRequest {
    private Integer progress;       // 0..100
    private String progressNote;    // yorum
    private LocalDate startDate;    // opsiyonel: ilk kez >0 ise set edilebilir
}