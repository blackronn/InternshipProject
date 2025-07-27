package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    List<Supervisor> findByInternId(Long internId);
    Supervisor findByInternIdAndDepartment_Id(Long internId, Long departmentId);
    void deleteByInternIdAndDepartment_Id(Long internId, Long departmentId);
    boolean existsByInternIdAndDepartment_IdAndEmail(Long internId, Long departmentId, String email);
}

