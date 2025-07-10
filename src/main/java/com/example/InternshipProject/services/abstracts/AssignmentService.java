package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.entities.concretes.Assignment;
import com.example.InternshipProject.services.dtos.requests.CreateAssignmentRequest;
import com.example.InternshipProject.services.dtos.responses.AssignmentResponse;

import java.util.List;

public interface AssignmentService {

    AssignmentResponse add(CreateAssignmentRequest request);

    List<AssignmentResponse> getAll();

    AssignmentResponse getById(int id);

    void delete(int id);

    AssignmentResponse update(int id, Assignment updatedAssignment);
}

