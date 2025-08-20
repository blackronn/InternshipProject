// src/main/java/com/example/InternshipProject/services/abstracts/OfficeService.java
package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.entities.concretes.Office;
import com.example.InternshipProject.services.dtos.requests.CreateOfficeRequest;
import com.example.InternshipProject.services.dtos.requests.UpdateOfficeRequest;
import com.example.InternshipProject.services.dtos.responses.OfficeResponse;

import java.util.List;

public interface OfficeService {
    OfficeResponse getOfficeByName(String name);
    void add(CreateOfficeRequest request);
    OfficeResponse update(int id, UpdateOfficeRequest request);
    OfficeResponse getOfficeByAddress(String address);// <-- YENİ METOT İMZASI
    List<OfficeResponse> getAllOffices();
    // İsterseniz update ve delete için de metotlar ekleyebilirsiniz.

    OfficeResponse getOfficeById(int id);
}