package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {

    // JPA, internId alanına göre sorgu oluşturacak
    List<Supervisor> findByInternId(Long internId);

}
