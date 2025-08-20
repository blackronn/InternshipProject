// src/main/java/com/example/InternshipProject/controllers/OfficeController.java
package com.example.InternshipProject.controllers;

import com.example.InternshipProject.services.abstracts.OfficeService;
import com.example.InternshipProject.services.dtos.requests.UpdateOfficeRequest;
import com.example.InternshipProject.services.dtos.responses.OfficeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
@CrossOrigin(origins = "http://localhost:8085")
public class OfficeController {

    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/by-name/{officeName}")
    public ResponseEntity<OfficeResponse> getOfficeByName(@PathVariable String officeName) {
        return ResponseEntity.ok(officeService.getOfficeByName(officeName));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OfficeResponse> update(@PathVariable int id, @RequestBody UpdateOfficeRequest request) {
        return ResponseEntity.ok(officeService.update(id, request));
    }
    @GetMapping("/by-address")
    public ResponseEntity<OfficeResponse> getByAddress(@RequestParam String address) {
        // Call the service method
        OfficeResponse response = officeService.getOfficeByAddress(address);

        // Check if the service returned null
        if (response == null) {
            // If null, the office was not found, so return HTTP 404
            return ResponseEntity.notFound().build();
        }

        // If not null, return HTTP 200 OK with the response body
        return ResponseEntity.ok(response);
    }

    // İsterseniz ofis eklemek için de bir endpoint ekleyebilirsiniz
    // @PostMapping
    // public void add(@RequestBody CreateOfficeRequest request) {
    //     officeService.add(request);
    // }
    @GetMapping
    public ResponseEntity<List<OfficeResponse>> getAllOffices(){
        return ResponseEntity.ok(officeService.getAllOffices());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OfficeResponse> getOfficeById(@PathVariable int id) {
        return ResponseEntity.ok(officeService.getOfficeById(id));
    }
}