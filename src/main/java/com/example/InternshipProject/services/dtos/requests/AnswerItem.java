package com.example.InternshipProject.services.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
public class AnswerItem {
    // Python'daki 'answers' listesinin içindeki objelere karşılık geliyor.
    private String category;
    private String answer;
}
