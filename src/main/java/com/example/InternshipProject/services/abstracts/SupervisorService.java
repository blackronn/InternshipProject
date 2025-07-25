package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.dtos.responses.SupervisorDto;
import com.example.InternshipProject.dtos.requests.CreateSupervisorRequest;

import java.util.List;

public interface SupervisorService {
    List<SupervisorDto> getAllSupervisors();
    void addSupervisor(CreateSupervisorRequest request);
}
