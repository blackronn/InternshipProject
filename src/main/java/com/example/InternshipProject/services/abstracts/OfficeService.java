// src/main/java/com/example/InternshipProject/services/abstracts/OfficeService.java
package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.services.dtos.requests.CreateOfficeRequest;
import com.example.InternshipProject.services.dtos.requests.UpdateOfficeRequest;
import com.example.InternshipProject.services.dtos.responses.OfficeResponse;

public interface OfficeService {
    OfficeResponse getOfficeByName(String name);
    void add(CreateOfficeRequest request);
    OfficeResponse update(int id, UpdateOfficeRequest request); // <-- YENİ METOT İMZASI

    // İsterseniz update ve delete için de metotlar ekleyebilirsiniz.
}