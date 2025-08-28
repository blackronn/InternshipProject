package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Assignment;
import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.entities.concretes.InternMentorRelation;
import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.repositories.AssignmentRepository;
import com.example.InternshipProject.repositories.InternMentorRelRepository;
import com.example.InternshipProject.repositories.InternRepository;
import com.example.InternshipProject.repositories.MentorRepository;
import com.example.InternshipProject.services.abstracts.AssignmentService;
import com.example.InternshipProject.services.dtos.requests.CreateAssignmentRequest;
import com.example.InternshipProject.services.dtos.responses.AssignmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final InternRepository internRepository;
    private final MentorRepository mentorRepository;
    private final InternMentorRelRepository internMentorRelRepository;

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
        assignment.setStartedAt(request.getStartedAt());

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
        Assignment assignment = assignmentRepository.findById((long) id).orElseThrow();
        return convertToResponse(assignment);
    }

    // AssignmentServiceImpl.java içinde

    @Override
    public AssignmentResponse update(int id, Assignment updatedAssignmentFromRequest) {
        // 1. Veritabanından güncellenecek olan mevcut görevi bul
        Assignment assignmentInDb = assignmentRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile görev bulunamadı: " + id));

        // 2. Gelen isteğin status'u null değilse devam et
        if (updatedAssignmentFromRequest.getStatus() != null) {
            String oldStatus = assignmentInDb.getStatus();
            String newStatus = updatedAssignmentFromRequest.getStatus();

            // 3. Statü gerçekten değişti mi diye kontrol et
            if (!newStatus.equals(oldStatus)) {
                // Statüyü yeni değeriyle güncelle
                assignmentInDb.setStatus(newStatus);

                // 4. YENİ EKLENEN MANTIK: Statüye göre tarihleri ayarla
                if ("In Progress".equals(newStatus)) {
                    // Eğer başlangıç tarihi henüz atanmamışsa, bugünün tarihi olarak ata
                    if (assignmentInDb.getStartedAt() == null) {
                        assignmentInDb.setStartedAt(LocalDate.now()); // Başlangıç tarihini bugünün tarihi yap
                    }
                } else if ("Completed".equals(newStatus)) {
                    // Eğer başlangıç tarihi boşsa, onu da doldur (örn: direkt Pending'den Completed'a çekilirse)
                    if (assignmentInDb.getAssignedAt() == null) {
                        assignmentInDb.setAssignedAt(LocalDate.now());
                    }
                    assignmentInDb.setCompletedAt(LocalDate.now()); // Bitiş tarihini bugünün tarihi yap
                }
            }
        }

        // 5. Değişiklikleri veritabanına kaydet
        Assignment savedAssignment = assignmentRepository.save(assignmentInDb);

        // 6. Sonucu DTO'ya çevirip döndür
        // Not: DTO'ya çeviren yardımcı metodunuzun adı farklı olabilir
        return convertToAssignmentResponse(savedAssignment);
    }

    @Override
    public void delete(int id) {
        assignmentRepository.deleteById((long) id);
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
        response.setStartedAt(assignment.getStartedAt());

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
    public AssignmentResponse convertToAssignmentResponse(Assignment assignment) {
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
        response.setStartedAt(assignment.getStartedAt());
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
    @Override
    public List<AssignmentResponse> getAssignmentsByInternEmail(String email) {
        Intern intern = internRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        List<Assignment> assignments = assignmentRepository.findByIntern(intern);

        return assignments.stream()
                .map(assignment -> {
                    AssignmentResponse response = new AssignmentResponse();
                    response.setId(assignment.getId());
                    response.setAssignmentName(assignment.getAssignmentName());
                    response.setAssignmentDesc(assignment.getAssignmentDesc());
                    response.setStatus(assignment.getStatus());
                    response.setPriority(assignment.getPriority());
                    response.setDueDate(assignment.getDueDate());
                    response.setAssignedAt(assignment.getAssignedAt());
                    response.setCompletedAt(assignment.getCompletedAt());
                    response.setStartedAt(assignment.getStartedAt());

                    response.setInternId(intern.getId());
                    response.setInternName(intern.getName() + " " + intern.getSurname());

                    if (assignment.getMentor() != null) {
                        response.setMentorId(assignment.getMentor().getId());
                        response.setMentorName(
                                assignment.getMentor().getName() + " " + assignment.getMentor().getSurname()
                        );
                    }

                    return response;
                })
                .toList();
    }
    @Override
    public Map<String, Long> getMentorAssignmentStats(String mentorEmail) {
        Mentor mentor = mentorRepository.findMentorByEmailIgnoreCase(mentorEmail)
                .orElseThrow(() -> new RuntimeException("Mentor bulunamadı"));

        List<InternMentorRelation> relations = internMentorRelRepository.findByMentor(mentor);

        List<Assignment> allAssignments = new ArrayList<>();
        for (InternMentorRelation rel : relations) {
            List<Assignment> internAssignments = assignmentRepository.findByIntern(rel.getIntern());
            allAssignments.addAll(internAssignments);
        }

        Map<String, Long> result = new HashMap<>();

        for (Assignment assignment : allAssignments) {
            if (assignment == null || assignment.getStatus() == null) continue;

            String status = assignment.getStatus().equalsIgnoreCase("completed") ? "Yapıldı" : "Yapılmadı";
            result.put(status, result.getOrDefault(status, 0L) + 1);
        }

        return result;
    }




}