// src/main/java/com/example/InternshipProject/controllers/OfficeController.java
package com.example.InternshipProject.controllers;

import com.example.InternshipProject.services.abstracts.OfficeService;
import com.example.InternshipProject.services.dtos.requests.UpdateOfficeRequest;
import com.example.InternshipProject.services.dtos.responses.OfficeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // İsterseniz ofis eklemek için de bir endpoint ekleyebilirsiniz
    // @PostMapping
    // public void add(@RequestBody CreateOfficeRequest request) {
    //     officeService.add(request);
    // }
}