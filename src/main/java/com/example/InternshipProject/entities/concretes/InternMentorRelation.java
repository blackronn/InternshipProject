package com.example.InternshipProject.entities.concretes;

import com.example.InternshipProject.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="INTERN_MENTOR_REL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternMentorRelation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RELATION_ID")
    private int relationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INTERN_ID")  // Foreign Key
    private Intern intern;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MENTOR_ID")  // Foreign Key
    private Mentor mentor;

    @Column(name="REL_START_DATE")
    private LocalDate relationStartDate;

    @Column(name="REL_END_DATE")
    private LocalDate relationEndDate;


}
