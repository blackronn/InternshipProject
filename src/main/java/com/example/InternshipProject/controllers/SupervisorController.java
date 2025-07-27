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

    private final SupervisorService supervisorService;

    // Tüm atamaları veya tek bir internId’ye ait atamaları getir
    @GetMapping
    public List<SupervisorDto> list(@RequestParam(required = false) Long internId) {
        if (internId != null) {
            return supervisorService.findByInternId(internId);
        }
        return supervisorService.getAllSupervisors();
    }

    // Yeni atama ekle (her zaman yeni kayıt, eski yöntem)
    @PostMapping
    public void add(@RequestBody CreateSupervisorRequest request) {
        supervisorService.addSupervisor(request);
    }

    // Unique olarak ekle/güncelle (frontendde yeni endpoint açmak istersen)
    @PostMapping("/unique")
    public void addOrUpdate(@RequestBody CreateSupervisorRequest request) {
        supervisorService.addOrUpdateSupervisor(request);
    }

    // Belirli atamayı ID ile sil (klasik)
    @DeleteMapping("/{id}")
    public void deleteSupervisor(@PathVariable Long id) {
        supervisorService.deleteSupervisorById(id);
    }

    // Belirli internId+departmentId ile sil (unique silme, opsiyonel)
    @DeleteMapping
    public void deleteSupervisorByInternAndDepartment(
            @RequestParam Long internId,
            @RequestParam Long departmentId) {
        supervisorService.deleteSupervisorByInternAndDepartment(internId, departmentId);
    }
}
