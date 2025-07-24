// src/main/java/com/example/InternshipProject/repositories/OfficeRepository.java

package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OfficeRepository extends JpaRepository<Office, Integer> {
    // Ofis adıyla arama yapacak metot
    Optional<Office> findByName(String name);
}