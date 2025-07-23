package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Assignment;
import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.repositories.AssignmentRepository;
import com.example.InternshipProject.services.abstracts.AssignmentService;
import com.example.InternshipProject.services.dtos.requests.CreateAssignmentRequest;
import com.example.InternshipProject.services.dtos.responses.AssignmentResponse;
import com.example.InternshipProject.services.dtos.responses.InternResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    // AssignmentServiceImpl.java sınıfının içinde

    @Override // AssignmentService interface'inden gelen metodu ezip üzerine yazdığımızı belirtir.
    public List<AssignmentResponse> findAssignmentsByInternId(Integer internId) {
        // 1. Adım: Repository'yi çağırarak veritabanından ilgili Assignment entity'lerini al.
        List<Assignment> assignments = assignmentRepository.findAssignmentsByInternId(internId);

        // 2. Adım: Gelen entity listesini, DTO listesine dönüştür.
        // Java Stream API kullanarak her bir "Assignment" nesnesini "AssignmentResponse" nesnesine çeviriyoruz.
        List<AssignmentResponse> responseList = assignments.stream()
                .map(this::convertToAssignmentResponse) // Her bir assignment için aşağıdaki yardımcı metodu çağır
                .collect(Collectors.toList());

        return responseList;
    }
    @Override
    public List<AssignmentResponse> findAssignmentsByMentorId(Integer mentorId) {
        List<Assignment> assignments = assignmentRepository.findAssignmentsByMentorId(mentorId);

        return assignments.stream()
                .map(this::convertToAssignmentResponse) // Mevcut dönüşüm metodumuzu kullanıyoruz
                .collect(Collectors.toList());
    }

    // Bu yardımcı metodu da sınıfınıza eklemeniz gerekebilir.
// Bu metot, tek bir Assignment entity'sini AssignmentResponse DTO'suna çevirir.
    private AssignmentResponse convertToAssignmentResponse(Assignment assignment) {
        // Eğer MapStruct gibi bir kütüphane kullanmıyorsanız, bu dönüşümü elle yapmalısınız.
        AssignmentResponse response = new AssignmentResponse();

        // Alanları entity'den DTO'ya kopyala
        response.setId(assignment.getId());
        response.setAssignmentName(assignment.getAssignmentName());
        response.setAssignmentDesc(assignment.getAssignmentDesc());
        response.setDueDate(assignment.getDueDate());
        response.setPriority(assignment.getPriority());
        response.setAssignedAt(assignment.getAssignedAt());
        response.setCompletedAt(assignment.getCompletedAt());
        response.setStatus(assignment.getStatus());
        // ... AssignmentResponse DTO'nuzda olan diğer tüm alanları burada set edin ...

        // İlişkili nesnelerin sadece ID'lerini eklemek genellikle yeterlidir.
        if (assignment.getIntern() != null) {
            // Stajyer nesnesini okunabilirlik için bir değişkene alıyoruz
            Intern intern = assignment.getIntern();

            // ID'yi set ediyoruz
            response.setInternId(intern.getId());

            // Adını ve soyadını birleştirip yeni 'internName' alanına set ediyoruz
            response.setInternName(intern.getName() + " " + intern.getSurname());
        }
        if (assignment.getMentor() != null) {
            Mentor mentor = assignment.getMentor(); // Mentor nesnesinin tamamını al
            response.setMentorId(mentor.getId());
            response.setMentorName(mentor.getName() + " " + mentor.getSurname()); // <-- YENİ SATIR
        }

        return response;
    }
}