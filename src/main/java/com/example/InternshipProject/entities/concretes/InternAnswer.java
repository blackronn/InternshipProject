package com.example.InternshipProject.entities.concretes;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "intern_answers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class InternAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "intern_id", nullable = false)
    private Intern intern;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "answer", nullable = false, length = 2000)
    private String answer;

    @Column(name = "answer_date", nullable = false)
    private LocalDate answerDate;
}