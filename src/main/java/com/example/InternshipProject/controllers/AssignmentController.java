package com.example.InternshipProject.controllers;

import com.example.InternshipProject.repositories.AssignmentRepository;
import com.example.InternshipProject.services.abstracts.AssignmentService;
import com.example.InternshipProject.services.dtos.requests.CreateAssignmentRequest;
<<<<<<< HEAD
=======
import com.example.InternshipProject.services.dtos.requests.UpdateAssignmentProgressRequest; // [ADDED]
>>>>>>> 049e957 (feat: backend project initial push)
import com.example.InternshipProject.services.dtos.responses.AssignmentResponse;
import com.example.InternshipProject.entities.concretes.Assignment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
=======
>>>>>>> 049e957 (feat: backend project initial push)
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.persistence.criteria.Predicate;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
<<<<<<< HEAD
@CrossOrigin(origins = "http://localhost:8085")
=======
>>>>>>> 049e957 (feat: backend project initial push)
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final AssignmentRepository assignmentRepository;

    @PostMapping
    public AssignmentResponse add(@RequestBody CreateAssignmentRequest request) {
        return assignmentService.add(request);
    }

    @GetMapping
    public List<AssignmentResponse> getAll() {
        return assignmentService.getAll();
    }

    @GetMapping("/{id}")
    public AssignmentResponse getById(@PathVariable int id) {
        return assignmentService.getById(id);
    }

    @PutMapping("/{id}")
    public AssignmentResponse update(@PathVariable int id, @RequestBody Assignment assignment) {
        return assignmentService.update(id, assignment);
    }

<<<<<<< HEAD
=======
    // [ADDED] Yüzde ve yorum güncelleme endpoint’i
    @PutMapping("/{id}/progress")
    public ResponseEntity<AssignmentResponse> updateProgress(
            @PathVariable long id,
            @RequestBody UpdateAssignmentProgressRequest request
    ) {
        return ResponseEntity.ok(assignmentService.updateProgress(id, request));
    }

>>>>>>> 049e957 (feat: backend project initial push)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        assignmentService.delete(id);
    }

    @GetMapping("/stats")
    public Map<String, Map<String, Long>> getAssignmentStats() {
        List<Assignment> assignments = assignmentRepository.findAll();

        Map<String, Map<String, Long>> result = new HashMap<>();

        for (Assignment assignment : assignments) {
            if (assignment == null) continue;

            if (assignment.getIntern() == null || assignment.getStatus() == null) {
                continue;
            }

            String email = assignment.getIntern().getEmail();
            String status = assignment.getStatus().equalsIgnoreCase("completed") ? "Yapıldı" : "Yapılmadı";

            result.putIfAbsent(email, new HashMap<>());
            Map<String, Long> userStats = result.get(email);
            userStats.put(status, userStats.getOrDefault(status, 0L) + 1);
        }

        return result;
    }

<<<<<<< HEAD

=======
>>>>>>> 049e957 (feat: backend project initial push)
    @GetMapping("{internId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsByInternId(@PathVariable Integer internId){
        List<AssignmentResponse> assignments=assignmentService.findAssignmentsByInternId(internId);
        return ResponseEntity.ok(assignments);
    }
<<<<<<< HEAD
=======

>>>>>>> 049e957 (feat: backend project initial push)
    @GetMapping("/by-mentor/{mentorId}")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsForMentorInterns(@PathVariable Integer mentorId) {
        List<AssignmentResponse> assignments = assignmentService.findAssignmentsByMentorId(mentorId);
        return ResponseEntity.ok(assignments);
    }
<<<<<<< HEAD
=======

>>>>>>> 049e957 (feat: backend project initial push)
    @GetMapping("/intern")
    public List<AssignmentResponse> getAssignmentsByEmail(@RequestParam String email) {
        return assignmentService.getAssignmentsByInternEmail(email);
    }
<<<<<<< HEAD
=======

>>>>>>> 049e957 (feat: backend project initial push)
    @GetMapping("/stats/mentor")
    public Map<String, Long> getMentorAssignmentStats(@RequestParam String email) {
        return assignmentService.getMentorAssignmentStats(email);
    }
<<<<<<< HEAD
=======

>>>>>>> 049e957 (feat: backend project initial push)
    @GetMapping("/paged")
    public ResponseEntity<Page<AssignmentResponse>> getPagedAssignments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "assignedAt") String sort,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam(required = false) Long mentorId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority
    ) {
        Pageable pageable = PageRequest.of(
                page, size,
                direction.equalsIgnoreCase("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending()
        );

        Specification<Assignment> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (mentorId != null) {
                predicates.add(cb.equal(root.get("mentor").get("id"), mentorId));
            }

            if (status != null && !status.isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("status")), status.toLowerCase()));
            }

            if (priority != null && !priority.isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("priority")), priority.toLowerCase()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<Assignment> assignmentPage = assignmentRepository.findAll(spec, pageable);
        Page<AssignmentResponse> responsePage = assignmentPage.map(assignmentService::convertToAssignmentResponse);

<<<<<<< HEAD

        return ResponseEntity.ok(responsePage);
    }




}

=======
        return ResponseEntity.ok(responsePage);
    }
}
>>>>>>> 049e957 (feat: backend project initial push)
