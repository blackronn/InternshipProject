package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.entities.concretes.Question;
import com.example.InternshipProject.services.dtos.requests.SubmitAnswerRequest;
import com.example.InternshipProject.services.dtos.responses.QuestionResponse;

import java.util.List;

public interface QuestionService {
    List<QuestionResponse> getTodaysQuestionsForDisplay(); // Eskisini DTO'lu yapalım
    void submitAnswers(List<SubmitAnswerRequest> answers);


}
