package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.entities.concretes.Assignment;
import com.example.InternshipProject.services.dtos.requests.CreateAssignmentRequest;
import com.example.InternshipProject.services.dtos.responses.AssignmentResponse;
<<<<<<< HEAD
=======
import com.example.InternshipProject.services.dtos.requests.UpdateAssignmentProgressRequest;
>>>>>>> 049e957 (feat: backend project initial push)

import java.util.List;
import java.util.Map;

public interface AssignmentService {

    AssignmentResponse add(CreateAssignmentRequest request);

    List<AssignmentResponse> getAll();

<<<<<<< HEAD
=======
    AssignmentResponse updateProgress(long id, UpdateAssignmentProgressRequest request);

>>>>>>> 049e957 (feat: backend project initial push)
    AssignmentResponse getById(int id);

    void delete(int id);

    AssignmentResponse update(int id, Assignment updatedAssignment);

    List<AssignmentResponse> findAssignmentsByInternId(Integer internId);

    List<AssignmentResponse> findAssignmentsByMentorId(Integer mentorId);

    List<AssignmentResponse> getAssignmentsByInternEmail(String email);

    Map<String, Long> getMentorAssignmentStats(String mentorEmail);


    AssignmentResponse convertToAssignmentResponse(Assignment assignment);
}

