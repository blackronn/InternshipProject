package com.example.InternshipProject.entities.concretes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "questions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category", nullable = false)
    private String category; // Örn: "Proaktiflik", "İşbirliği / İletişim", "Süreç Memnuniyeti"

    @Column(name = "question_text", nullable = false, length = 500)
    private String text; // Sorunun kendisi, örn: "Bugün karşılaştığın bir zorluk oldu mu?"
}