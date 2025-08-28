package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Evaluation;
import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.entities.concretes.InternMentorRelation;
import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.repositories.EvaluationRepository;
import com.example.InternshipProject.repositories.InternMentorRelRepository;
import com.example.InternshipProject.repositories.InternRepository;
import com.example.InternshipProject.repositories.MentorRepository;
import com.example.InternshipProject.services.abstracts.EvaluationService;
import com.example.InternshipProject.services.dtos.requests.CreateEvaluationRequest;
import com.example.InternshipProject.services.dtos.requests.UpdateEvaluationRequest;
import com.example.InternshipProject.services.dtos.responses.EvaluationResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final InternRepository internRepository;
    private final MentorRepository mentorRepository;
    private final InternMentorRelRepository internMentorRelRepository;

    public EvaluationServiceImpl(EvaluationRepository evaluationRepository, InternRepository internRepository, MentorRepository mentorRepository, InternMentorRelRepository internMentorRelRepository) {
        this.evaluationRepository = evaluationRepository;
        this.internRepository = internRepository;
        this.mentorRepository = mentorRepository;
        this.internMentorRelRepository = internMentorRelRepository;
    }

    @Override
    @Transactional
    public EvaluationResponse createEvaluation(CreateEvaluationRequest request) {
        // --- Bu kısımdaki kurallar aynı kalıyor ---
        List<InternMentorRelation> relations = internMentorRelRepository.findByIntern_Id(request.getInternId());

        if (relations.isEmpty()) {
            throw new RuntimeException("No mentor assigned to this intern with id: " + request.getInternId());
        }

        boolean isAuthorized = relations.stream()
                .anyMatch(relation -> relation.getMentor().getId() == request.getMentorId());

        if (!isAuthorized) {
            throw new RuntimeException("Authorization Failed: The requesting mentor (ID: " + request.getMentorId() +
                    ") is not assigned to this intern (ID: " + request.getInternId() + ").");
        }

        Optional<Evaluation> existingEvaluation = evaluationRepository.findByMentor_IdAndIntern_Id(
                request.getMentorId(),
                request.getInternId()
        );

        if (existingEvaluation.isPresent()) {
            throw new RuntimeException("This mentor has already evaluated this intern.");
        }

        Intern intern = internRepository.findById(request.getInternId())
                .orElseThrow(() -> new RuntimeException("Intern not found"));
        Mentor mentor = mentorRepository.findById(request.getMentorId())
                .orElseThrow(() -> new RuntimeException("Mentor not found"));

        // --- DEĞİŞEN KISIM BAŞLIYOR ---
        Evaluation evaluation = new Evaluation();
        evaluation.setIntern(intern);
        evaluation.setMentor(mentor);

        // Frontend'den gelen yeni alanları set ediyoruz
        evaluation.setCommunicationRating(request.getCommunicationRating());
        evaluation.setTeamworkRating(request.getTeamworkRating());
        evaluation.setProblemSolvingRating(request.getProblemSolvingRating()); // YENİ
        evaluation.setResponsibilityRating(request.getResponsibilityRating()); // YENİ
        evaluation.setTimeManagementRating(request.getTimeManagementRating()); // YENİ
        evaluation.setComments(request.getComments());

        Evaluation savedEvaluation = evaluationRepository.save(evaluation);
        return convertToResponse(savedEvaluation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EvaluationResponse> getEvaluationsByInternId(int internId) {
        // Not: Repository'nizde bu metodun adının findByIntern_Id olduğundan emin olun.
        List<Evaluation> evaluations = evaluationRepository.findByInternId(internId);
        return evaluations.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // getEvaluationByMentorAndIntern metodunu da ekleyelim (bir önceki adımda konuşmuştuk)
    @Override
    @Transactional(readOnly = true)
    public EvaluationResponse getEvaluationByMentorAndIntern(int mentorId, int internId) {
        return evaluationRepository.findByMentor_IdAndIntern_Id(mentorId, internId)
                .map(this::convertToResponse)
                .orElse(null);
    }

    @Override
    @Transactional
    public EvaluationResponse deleteEvaluation(int evaluationId, int requestingMentorId) {
        Evaluation evaluationToDelete = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new RuntimeException("Evaluation Not Found with id " + evaluationId));

        if (evaluationToDelete.getMentor().getId() != requestingMentorId) {
            throw new RuntimeException("Authorization failed: You can only delete your own evaluations.");
        }

        EvaluationResponse response = convertToResponse(evaluationToDelete);
        evaluationRepository.delete(evaluationToDelete);
        return response;
    }

    @Override
    @Transactional
    public EvaluationResponse updateEvaluation(int evaluationId, UpdateEvaluationRequest request) {
        Evaluation existingEvaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new RuntimeException("Evaluation Not Found with id " + evaluationId));

        if (existingEvaluation.getMentor().getId() != request.getRequestingMentorId()) {
            throw new RuntimeException("Authorization failed: You can only update your own evaluations.");
        }

        // --- DEĞİŞEN KISIM BAŞLIYOR ---
        // Frontend'den gelen yeni alanlarla güncelliyoruz
        existingEvaluation.setComments(request.getComments());
        existingEvaluation.setCommunicationRating(request.getCommunicationRating());
        existingEvaluation.setTeamworkRating(request.getTeamworkRating());
        existingEvaluation.setProblemSolvingRating(request.getProblemSolvingRating()); // YENİ
        existingEvaluation.setResponsibilityRating(request.getResponsibilityRating()); // YENİ
        existingEvaluation.setTimeManagementRating(request.getTimeManagementRating()); // YENİ

        Evaluation updatedEvaluation = evaluationRepository.save(existingEvaluation);
        return convertToResponse(updatedEvaluation);
    }

    private EvaluationResponse convertToResponse(Evaluation evaluation) {
        EvaluationResponse response = new EvaluationResponse();
        response.setId(evaluation.getId());

        // --- DEĞİŞEN KISIM BAŞLIYOR ---
        // DTO'yu yeni alanlarla dolduruyoruz
        response.setCommunicationRating(evaluation.getCommunicationRating());
        response.setTeamworkRating(evaluation.getTeamworkRating());
        response.setProblemSolvingRating(evaluation.getProblemSolvingRating()); // YENİ
        response.setResponsibilityRating(evaluation.getResponsibilityRating()); // YENİ
        response.setTimeManagementRating(evaluation.getTimeManagementRating()); // YENİ
        response.setComments(evaluation.getComments());

        if (evaluation.getIntern() != null) {
            response.setInternName(evaluation.getIntern().getName() + " " + evaluation.getIntern().getSurname());
        }
        if (evaluation.getMentor() != null) {
            response.setMentorName(evaluation.getMentor().getName() + " " + evaluation.getMentor().getSurname());
        }
        return response;
    }
}