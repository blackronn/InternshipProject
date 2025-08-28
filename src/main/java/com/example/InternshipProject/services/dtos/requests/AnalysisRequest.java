package com.example.InternshipProject.services.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class AnalysisRequest {
    // Python'daki 'report_data'ya karşılık geliyor.
    private String report_text = ""; // PDF'imiz olmadığı için şimdilik boş.
    private List<AnswerItem> answers;
}



