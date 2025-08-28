package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.InternAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface InternAnswerRepository extends JpaRepository<InternAnswer, Long> {
    // Belirli bir stajyerin, belirli bir tarihteki tüm cevaplarını bulur.
    List<InternAnswer> findByInternIdAndAnswerDate(Integer internId, LocalDate date);

    void deleteByAnswerDateBefore(LocalDate date);
}