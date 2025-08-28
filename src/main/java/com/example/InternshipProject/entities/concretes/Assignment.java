package com.example.InternshipProject.entities.concretes;

import com.example.InternshipProject.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="ASSIGNMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Assignment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSIGNMENT_ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "INTERN_ID")  // Foreign Key
    private Intern intern;

    @ManyToOne
    @JoinColumn(name = "MENTOR_ID")  // Foreign Key
    private Mentor mentor;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "ASSIGNMENT_DESC")
    private String assignmentDesc;

    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "ASSIGNED_AT")
    private LocalDate assignedAt;

    @Column(name = "COMPLETED_AT")
    private LocalDate completedAt;

    @Column(name = "ASSIGNMENT_NAME")
    private String assignmentName;

    @Column(name="STARTED_AT")
    private LocalDate startedAt;
<<<<<<< HEAD
=======
    // [ADDED] ilerleme yüzdesi (0..100) ve yorum
    @Column(name = "PROGRESS")
    private Integer progress;   // null gelirse service 0'a çeker

    @Column(name = "PROGRESS_NOTE", length = 2000)
    private String progressNote; // kullanıcı yorumu

    // İsteğe bağlı: default değerler için
    @PrePersist
    public void onCreate() {
        if (this.progress == null) this.progress = 0;
        if (this.getStatus() == null) this.setStatus("Not Started"); // mevcut yapıya uygun string kullanıyoruz
    }
>>>>>>> 049e957 (feat: backend project initial push)

}
