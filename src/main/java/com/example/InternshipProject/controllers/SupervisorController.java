package com.example.InternshipProject.controllers;

import com.example.InternshipProject.dtos.responses.SupervisorDto;
import com.example.InternshipProject.services.abstracts.SupervisorService;
import com.example.InternshipProject.dtos.requests.CreateSupervisorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supervisors")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8085")
public class SupervisorController {

    private final SupervisorService supervisorService;  // ← buradaki ad

    // Tüm atamaları veya tek bir internId’ye ait atamaları getir
    @GetMapping
    public List<SupervisorDto> list(@RequestParam(required = false) Long internId) {
        if (internId != null) {
            // <–– doğru servis örneğini kullan
            return supervisorService.findByInternId(internId);
        }
        // eski davranış: tümünü çek
        return supervisorService.getAllSupervisors();
    }

    // Yeni atama ekle
    @PostMapping
    public void add(@RequestBody CreateSupervisorRequest request) {
        supervisorService.addSupervisor(request);
    }
}
