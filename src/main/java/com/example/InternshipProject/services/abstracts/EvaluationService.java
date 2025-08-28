package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.services.dtos.requests.CreateEvaluationRequest;
import com.example.InternshipProject.services.dtos.requests.UpdateEvaluationRequest;
import com.example.InternshipProject.services.dtos.responses.EvaluationResponse;
import java.util.List;

public interface EvaluationService {
    EvaluationResponse createEvaluation(CreateEvaluationRequest request);
    List<EvaluationResponse> getEvaluationsByInternId(int internId);
    EvaluationResponse deleteEvaluation(int evaluationId, int requestingMentorId);

    EvaluationResponse updateEvaluation(int id, UpdateEvaluationRequest request);
    EvaluationResponse getEvaluationByMentorAndIntern(int mentorId, int internId);
}