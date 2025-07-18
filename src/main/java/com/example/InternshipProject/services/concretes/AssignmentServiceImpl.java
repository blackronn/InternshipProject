package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Assignment;
import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.repositories.AssignmentRepository;
import com.example.InternshipProject.services.abstracts.AssignmentService;
import com.example.InternshipProject.services.dtos.requests.CreateAssignmentRequest;
import com.example.InternshipProject.services.dtos.responses.AssignmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Override
    public AssignmentResponse add(CreateAssignmentRequest request) {
        Assignment assignment = new Assignment();

        assignment.setAssignmentName(request.getAssignmentName());
        assignment.setAssignmentDesc(request.getAssignmentDesc());
        assignment.setStatus(request.getStatus());
        assignment.setPriority(request.getPriority());
        assignment.setDueDate(request.getDueDate());
        assignment.setAssignedAt(request.getAssignedAt());
        assignment.setCompletedAt(request.getCompletedAt());

        Intern intern = new Intern();
        intern.setId(request.getInternId());
        assignment.setIntern(intern);

        Mentor mentor = new Mentor();
        mentor.setId(request.getMentorId());
        assignment.setMentor(mentor);

        Assignment saved = assignmentRepository.save(assignment);

        return convertToResponse(saved);
    }

    @Override
    public List<AssignmentResponse> getAll() {
        return assignmentRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AssignmentResponse getById(int id) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow();
        return convertToResponse(assignment);
    }

    @Override
    public AssignmentResponse update(int id, Assignment updatedAssignmentFromRequest) {

        Assignment existingAssignmentInDb = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile görev bulunamadı: " + id));

        if (updatedAssignmentFromRequest.getStatus() != null) {
            existingAssignmentInDb.setStatus(updatedAssignmentFromRequest.getStatus());
        }

        Assignment updated = assignmentRepository.save(existingAssignmentInDb);

        return convertToResponse(updated);
    }

    @Override
    public void delete(int id) {
        assignmentRepository.deleteById(id);
    }

    private AssignmentResponse convertToResponse(Assignment assignment) {
        AssignmentResponse response = new AssignmentResponse();

        response.setId(assignment.getId());
        response.setAssignmentName(assignment.getAssignmentName());
        response.setAssignmentDesc(assignment.getAssignmentDesc());
        response.setStatus(assignment.getStatus());
        response.setPriority(assignment.getPriority());
        response.setDueDate(assignment.getDueDate());
        response.setAssignedAt(assignment.getAssignedAt());
        response.setCompletedAt(assignment.getCompletedAt());
        response.setInternId(assignment.getIntern().getId());
        response.setMentorId(assignment.getMentor().getId());

        return response;
    }
}