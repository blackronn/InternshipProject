// src/main/java/com/example/InternshipProject/services/dtos/requests/CreateOfficeRequest.java
package com.example.InternshipProject.services.dtos.requests;

import lombok.Data;

@Data
public class CreateOfficeRequest {
    private String name;
    private String address;
    private String phoneNumber;
    private String transportDetails;
}