package com.example.InternshipProject.controllers;

import com.example.InternshipProject.dtos.requests.CreateSupervisorRequest;
import com.example.InternshipProject.dtos.responses.SupervisorDto;
import com.example.InternshipProject.services.abstracts.SupervisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supervisors")
@RequiredArgsConstructor
public class SupervisorController {

    private final SupervisorService supervisorService;

    // Tüm çalışanları getir
    @GetMapping
    public List<SupervisorDto> getAll() {
        return supervisorService.getAllSupervisors();
    }

    // Yeni çalışan ekle
    @PostMapping
    public void add(@RequestBody CreateSupervisorRequest request) {
        supervisorService.addSupervisor(request);
    }
}
