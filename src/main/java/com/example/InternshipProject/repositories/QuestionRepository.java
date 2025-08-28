package com.example.InternshipProject.repositories;

import com.example.InternshipProject.entities.concretes.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // Belirli bir kategoriye ait tüm soruları bulur.
    List<Question> findByCategory(String category);
}