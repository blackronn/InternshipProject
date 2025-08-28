// src/main/java/com/example/InternshipProject/repositories/FaqRepository.java

package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Integer> {
    // Şimdilik özel bir metoda ihtiyacımız yok, JpaRepository'nin findAll() metodu yeterli.
}
