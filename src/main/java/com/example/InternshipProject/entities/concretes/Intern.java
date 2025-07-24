package com.example.InternshipProject.entities.concretes;

import com.example.InternshipProject.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="INTERN")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Intern extends BaseEntity {

    @Id
    @Column(name="INTERN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="NAME")
    private String name;

    @Column(name="SURNAME")
    private String surname;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PHONE_NUMBER")
    private String phoneNumber;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @Column(name="UNIVERSITY")
    private String university;

    @Column(name="DEPARTMENT")
    private String department;
}
