package com.example.InternshipProject.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="MENTOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mentor {

    @Id
    @Column(name="MENTOR_ID")
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

    @Column(name="TITLE")
    private String title;
}
