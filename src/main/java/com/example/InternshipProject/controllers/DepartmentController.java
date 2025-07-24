package com.example.InternshipProject.controllers;
import com.example.InternshipProject.services.dtos.responses.DepartmentDto;
import com.example.InternshipProject.services.abstracts.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8085")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/departments")
    public List<DepartmentDto> list(
            @RequestParam(required = false) String university
    ) {
        if (university == null || university.isBlank()) {
            // parametre yoksa → tüm bölümler
            return departmentService.getAllDepartments();
        }
        // parametre varsa → sadece o üniversitenin bölümleri
        return departmentService.getByUniversity(university);
    }
}
