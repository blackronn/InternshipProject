package com.example.InternshipProject.entities.concretes;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Entity
public class Todo {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String task;
    private boolean done;

    private String internEmail; // Kim eklediyse onun email'i

}
