package com.example.InternshipProject.entities.concretes;

import com.example.InternshipProject.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.InternshipProject.entities.Gender;

@Entity
@Table(name="MENTOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mentor extends BaseEntity {

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

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender = Gender.UNSPECIFIED;

}
