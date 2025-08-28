// src/main/java/com/example/InternshipProject/entities/concretes/Faq.java

package com.example.InternshipProject.entities.concretes;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "faq")
@Data
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question", length = 512, nullable = false)
    private String question;

    @Column(name = "answer", length = 2048, nullable = false)
    private String answer;
}