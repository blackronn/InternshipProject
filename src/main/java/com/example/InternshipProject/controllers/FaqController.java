// src/main/java/com/example/InternshipProject/controllers/FaqController.java
package com.example.InternshipProject.controllers;

import com.example.InternshipProject.services.abstracts.FaqService;
import com.example.InternshipProject.services.dtos.responses.FaqResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/faqs")
@CrossOrigin(origins = "http://localhost:8085")
public class FaqController {

    private final FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping
    public ResponseEntity<List<FaqResponse>> getAllFaqs() {
        return ResponseEntity.ok(faqService.getAllFaqs());
    }
}