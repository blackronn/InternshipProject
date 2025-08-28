// src/main/java/com/example/InternshipProject/services/abstracts/FaqService.java
package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.services.dtos.responses.FaqResponse;
import java.util.List;

public interface FaqService {
    List<FaqResponse> getAllFaqs();
}
