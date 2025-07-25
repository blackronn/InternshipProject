package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.dtos.requests.CreateSupervisorRequest;
import com.example.InternshipProject.dtos.responses.SupervisorDto;
import com.example.InternshipProject.entities.concretes.Supervisor;
import com.example.InternshipProject.entities.concretes.SupervisorDepartment;
import com.example.InternshipProject.repositories.SupervisorDepartmentRepository;
import com.example.InternshipProject.repositories.SupervisorRepository;
import com.example.InternshipProject.services.abstracts.SupervisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupervisorServiceImpl implements SupervisorService {

    private final SupervisorRepository supervisorRepository;
    private final SupervisorDepartmentRepository departmentRepository;

    @Override
    public List<SupervisorDto> getAllSupervisors() {
        return supervisorRepository.findAll().stream().map(entity -> {
            SupervisorDto dto = new SupervisorDto();
            dto.setDepartmentName(entity.getDepartment().getName());
            dto.setSupervisorName(entity.getName());
            dto.setSupervisorEmail(entity.getEmail());
            return dto;
        }).toList();
    }

    @Override
    public void addSupervisor(CreateSupervisorRequest request) {
        Supervisor supervisor = new Supervisor();
        supervisor.setInternId(request.getInternId());
        supervisor.setName(request.getSupervisorName());
        supervisor.setEmail(request.getSupervisorEmail());

        SupervisorDepartment department = departmentRepository.findById(request.getSupervisorDepartmentId())
                .orElseThrow(() -> new RuntimeException("Departman bulunamadı"));

        supervisor.setDepartment(department);

        supervisorRepository.save(supervisor);
    }
}
