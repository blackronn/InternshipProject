package com.example.InternshipProject.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="INTERN_MENTOR_REL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternMentorRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RELATION_ID")
    private int relationId;

    @OneToOne
    @JoinColumn(name = "INTERN_ID")  // Foreign Key
    private Intern intern;

    @OneToOne
    @JoinColumn(name = "MENTOR_ID")  // Foreign Key
    private Mentor mentor;
}
