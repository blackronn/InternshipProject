package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.DailyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface DailyQuestionRepository extends JpaRepository<DailyQuestion, Long> {
    // Belirli bir tarihe ait seçilmiş soruları bulur.
    List<DailyQuestion> findByDate(LocalDate date);
}