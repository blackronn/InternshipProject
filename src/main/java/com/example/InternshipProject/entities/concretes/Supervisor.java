package com.example.InternshipProject.entities.concretes;

import com.example.InternshipProject.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "INTERN_SPRVSR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supervisor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INTERN_SPRVSR_ID")
    private Long id;

    @Column(name = "INTERN_ID")
    private Long internId;

    @ManyToOne
    @JoinColumn(name = "SPRVSR_DEPRMNT_ID")
    private SupervisorDepartment department;

    @Column(name = "SPRVSR_NAME")
    private String name;

    @Column(name = "SPRVSR_EMAIL")
    private String email;
}
