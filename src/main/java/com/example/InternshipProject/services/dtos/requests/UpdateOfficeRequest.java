// src/main/java/com/example/InternshipProject/services/dtos/requests/UpdateOfficeRequest.java

package com.example.InternshipProject.services.dtos.requests;

import lombok.Data;

@Data
public class UpdateOfficeRequest {
    // Sadece güncellenmesine izin verdiğimiz alanlar
    private String name;
    private String address;
    private String phoneNumber;
    private String transportDetails;
}