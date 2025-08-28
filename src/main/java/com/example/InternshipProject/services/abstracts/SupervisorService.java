package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.dtos.responses.SupervisorDto;
import com.example.InternshipProject.dtos.requests.CreateSupervisorRequest;

import java.util.List;

public interface SupervisorService {

    // 1) Tüm atamalar
    List<SupervisorDto> getAllSupervisors();

    // 2) Yeni atama ekle
    void addSupervisor(CreateSupervisorRequest request);

    // 3) Sadece bir internId'ye ait atamaları döndür
    List<SupervisorDto> findByInternId(Long internId);

    void deleteSupervisorById(Long id);
    void addOrUpdateSupervisor(CreateSupervisorRequest request); //     id/department unique olacak
    void deleteSupervisorByInternAndDepartment(Long internId, Long departmentId);

}
