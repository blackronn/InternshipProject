// src/main/java/com/example/InternshipProject/entities/concretes/Office.java

package com.example.InternshipProject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "offices")
@Data
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name; // Örn: "İzmir", "Ankara"

    @Column(name = "address", length = 512)
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "transport_details", length = 1024)
    private String transportDetails;

    // Bir ofisin birden fazla stajyeri olabileceğini belirten ilişki
    // mappedBy = "office", Intern entity'sindeki alan adıyla aynı olmalı
    @OneToMany(mappedBy = "office")
    @JsonIgnore // Bu, JSON'a çevrilirken sonsuz döngüyü engeller
    private List<Intern> interns;
}