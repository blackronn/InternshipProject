package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Office;
import com.example.InternshipProject.repositories.OfficeRepository;
import com.example.InternshipProject.services.abstracts.OfficeService;
import com.example.InternshipProject.services.dtos.requests.CreateOfficeRequest;
import com.example.InternshipProject.services.dtos.requests.UpdateOfficeRequest;
import com.example.InternshipProject.services.dtos.responses.OfficeResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // Make sure this import is present

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public OfficeResponse getOfficeByName(String name) {
        // Assuming findByName returns Optional<Office>
        Office office = officeRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Ofis bulunamadı: " + name));
        return convertToOfficeResponse(office);
    }

    // --- THIS IS THE UPDATED METHOD ---
    @Override
    public OfficeResponse getOfficeByAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return null;
        }

        String locationKeyword = findLocationKeyword(address);
        if (locationKeyword == null) {
            return null;
        }

        Optional<Office> officeOptional = officeRepository.findByDistrictIgnoreCase(locationKeyword);
        if (officeOptional.isEmpty()) {
            return null;
        }

        Office office = officeOptional.get();
        OfficeResponse response = convertToOfficeResponse(office);
        // Overwrite the address in the response with the user's full address from Azure
        response.setAddress(address);
        return response;
    }

    @Override
    public List<OfficeResponse> getAllOffices() {
        List<Office> offices=  officeRepository.findAll();
        List<OfficeResponse> responses = new ArrayList<>();
        for(Office office : offices){
            OfficeResponse response = new OfficeResponse();
            response.setId(office.getId());
            response.setName(office.getName());
            responses.add(response);
        }
        return responses;

    }

    @Override
    public OfficeResponse getOfficeById(int id) {
        Office office = officeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ofis bulunamadı: " + id));
        return convertToOfficeResponse(office);
    }


    private String findLocationKeyword(String address) {
        String addressLower = address.toLowerCase();
        if (addressLower.contains("urla")) return "Urla";
        if (addressLower.contains("ankara")) return "Ankara";
        if (addressLower.contains("esenler")) return "Esenler";
        if (addressLower.contains("kadıköy")) return "Kadıköy";
        return null;
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
        Office officeInDb = officeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile ofis bulunamadı: " + id));

        officeInDb.setName(request.getName());
        officeInDb.setAddress(request.getAddress());
        officeInDb.setPhoneNumber(request.getPhoneNumber());
        officeInDb.setTransportDetails(request.getTransportDetails());

        Office updatedOffice = officeRepository.save(officeInDb);
        return convertToOfficeResponse(updatedOffice);
    }

    // Your existing helper method is used
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