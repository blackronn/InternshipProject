package com.example.InternshipProject.controllers;

import com.example.InternshipProject.services.abstracts.QuestionService;
import com.example.InternshipProject.services.dtos.requests.SubmitAnswerRequest;
import com.example.InternshipProject.services.dtos.responses.QuestionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "http://localhost:8085")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Frontend bu endpoint'i çağırarak stajyere göstereceği 3 soruyu alır.
    @GetMapping("/today")
    public ResponseEntity<List<QuestionResponse>> getTodaysQuestions() {
        return ResponseEntity.ok(questionService.getTodaysQuestionsForDisplay());
    }

    // Frontend, stajyerin doldurduğu 3 cevabı bu endpoint'e gönderir.
    @PostMapping("/submit")
    public ResponseEntity<Void> submitAnswers(@RequestBody List<SubmitAnswerRequest> answers) {
        questionService.submitAnswers(answers);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}