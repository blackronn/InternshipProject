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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupervisorServiceImpl implements SupervisorService {

    private final SupervisorRepository supervisorRepository;
    private final SupervisorDepartmentRepository departmentRepository;

    @Override
    public List<SupervisorDto> getAllSupervisors() {
        return supervisorRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SupervisorDto> findByInternId(Long internId) {
        return supervisorRepository.findByInternId(internId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ESKİ: Her zaman yeni kayıt eklerdi. Şimdi duplicate varsa eklemeyecek!
    @Override
    public void addSupervisor(CreateSupervisorRequest request) {
        // DİKKAT! Aynı intern, departman ve email ile kayıt var mı kontrolü
        boolean exists = supervisorRepository.existsByInternIdAndDepartment_IdAndEmail(
                request.getInternId(),
                request.getSupervisorDepartmentId(),
                request.getSupervisorEmail()
        );
        if (exists) {
            // Zaten aynı kayıt var, yeni kayıt eklemiyoruz!
            return;
        }

        Supervisor sup = new Supervisor();
        sup.setInternId(request.getInternId());

        SupervisorDepartment dept = departmentRepository.findById(request.getSupervisorDepartmentId())
                .orElseThrow(() -> new RuntimeException("Departman bulunamadı: " + request.getSupervisorDepartmentId()));
        sup.setDepartment(dept);

        sup.setName(request.getSupervisorName());
        sup.setEmail(request.getSupervisorEmail());

        supervisorRepository.save(sup);
    }

    // Yeni: aynı intern-departman için varsa günceller yoksa ekler
    @Override
    public void addOrUpdateSupervisor(CreateSupervisorRequest request) {
        Supervisor existing = supervisorRepository.findByInternIdAndDepartment_Id(
                request.getInternId(),
                request.getSupervisorDepartmentId()
        );
        SupervisorDepartment dept = departmentRepository.findById(request.getSupervisorDepartmentId())
                .orElseThrow(() -> new RuntimeException("Departman bulunamadı: " + request.getSupervisorDepartmentId()));

        if (existing != null) {
            existing.setName(request.getSupervisorName());
            existing.setEmail(request.getSupervisorEmail());
            existing.setDepartment(dept);
            supervisorRepository.save(existing);
        } else {
            Supervisor sup = new Supervisor();
            sup.setInternId(request.getInternId());
            sup.setName(request.getSupervisorName());
            sup.setEmail(request.getSupervisorEmail());
            sup.setDepartment(dept);
            supervisorRepository.save(sup);
        }
    }

    // Unique silme: intern+department
    @Override
    public void deleteSupervisorByInternAndDepartment(Long internId, Long departmentId) {
        supervisorRepository.deleteByInternIdAndDepartment_Id(internId, departmentId);
    }

    // ID ile silme (klasik)
    @Override
    public void deleteSupervisorById(Long id) {
        supervisorRepository.deleteById(id);
    }

    // DTO dönüşümünü tek bir yerde topladık
    private SupervisorDto toDto(Supervisor s) {
        SupervisorDto dto = new SupervisorDto();
        dto.setId(s.getId());
        dto.setInternId(s.getInternId());
        dto.setDepartmentId(s.getDepartment().getId());
        dto.setDepartmentName(s.getDepartment().getName());
        dto.setSupervisorName(s.getName());
        dto.setSupervisorEmail(s.getEmail());
        return dto;
    }
}

