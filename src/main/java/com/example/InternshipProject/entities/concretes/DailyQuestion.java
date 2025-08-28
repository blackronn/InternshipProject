package com.example.InternshipProject.entities.concretes;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "daily_questions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DailyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "date", nullable = false)
    private LocalDate date;
}