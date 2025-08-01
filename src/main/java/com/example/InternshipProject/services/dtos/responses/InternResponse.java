package com.example.InternshipProject.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternResponse {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String university;
    private String department;
    private String mentorName;
    private String mentorEmail;
    private OfficeResponse office;// "Ad Soyad" format or null
    private String managerEmail;


    private String startDate;
    private String endDate;

}
