// src/main/java/com/example/InternshipProject/services/dtos/responses/OfficeResponse.java
package com.example.InternshipProject.services.dtos.responses;

import lombok.Data;

@Data
public class OfficeResponse {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private String transportDetails;
}