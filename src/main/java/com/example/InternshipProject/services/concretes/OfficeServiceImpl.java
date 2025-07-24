// src/main/java/com/example/InternshipProject/services/concretes/OfficeServiceImpl.java
package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Office;
import com.example.InternshipProject.repositories.OfficeRepository;
import com.example.InternshipProject.services.abstracts.OfficeService;
import com.example.InternshipProject.services.dtos.requests.CreateOfficeRequest;
import com.example.InternshipProject.services.dtos.requests.UpdateOfficeRequest;
import com.example.InternshipProject.services.dtos.responses.OfficeResponse;
import org.springframework.stereotype.Service;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public OfficeResponse getOfficeByName(String name) {
        Office office = officeRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Ofis bulunamadı: " + name));
        return convertToOfficeResponse(office);
    }

    @Override
    public void add(CreateOfficeRequest request) {
        Office office = new Office();
        office.setName(request.getName());
        office.setAddress(request.getAddress());
        office.setPhoneNumber(request.getPhoneNumber());
        office.setTransportDetails(request.getTransportDetails());
        officeRepository.save(office);
    }
    @Override
    public OfficeResponse update(int id, UpdateOfficeRequest request) {
        // 1. ID ile mevcut ofisi veritabanında bul. Bulamazsan hata fırlat.
        Office officeInDb = officeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile ofis bulunamadı: " + id));

        // 2. Gelen isteğin içindeki yeni verileri mevcut ofis nesnesine ata.
        officeInDb.setName(request.getName());
        officeInDb.setAddress(request.getAddress());
        officeInDb.setPhoneNumber(request.getPhoneNumber());
        officeInDb.setTransportDetails(request.getTransportDetails());

        // 3. Güncellenmiş nesneyi veritabanına kaydet.
        Office updatedOffice = officeRepository.save(officeInDb);

        // 4. Sonucu Response DTO'suna çevirip geri döndür.
        return convertToOfficeResponse(updatedOffice);
    }

    // YARDIMCI DÖNÜŞÜM METODU
    private OfficeResponse convertToOfficeResponse(Office office) {
        OfficeResponse response = new OfficeResponse();
        response.setId(office.getId());
        response.setName(office.getName());
        response.setAddress(office.getAddress());
        response.setPhoneNumber(office.getPhoneNumber());
        response.setTransportDetails(office.getTransportDetails());
        return response;
    }
}