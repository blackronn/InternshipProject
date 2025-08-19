package com.example.InternshipProject.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="EVALUATIONS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EVALUATION_ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="INTERN_ID", nullable = false)
    private Intern intern;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MENTOR_ID", nullable = false)
    private Mentor mentor;

    @Column(name="COMMUNICATION_RATING")
    private int communicationRating; // Bu zaten vardı

    @Column(name="TEAMWORK_RATING")
    private int teamworkRating; // Bu zaten vardı

    @Column(name="PROBLEM_SOLVING_RATING")
    private int problemSolvingRating; // YENİ

    @Column(name="RESPONSIBILITY_RATING")
    private int responsibilityRating; // YENİ

    @Column(name="TIME_MANAGEMENT_RATING")
    private int timeManagementRating; // YENİ

    @Lob
    @Column(name="COMMENTS")
    private String comments;
}