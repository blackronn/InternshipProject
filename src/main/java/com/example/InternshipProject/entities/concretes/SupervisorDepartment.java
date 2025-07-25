package com.example.InternshipProject.entities.concretes;

import com.example.InternshipProject.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SPRVSR_DEPRMNT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupervisorDepartment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPRVSR_DEPRMNT_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;
}
