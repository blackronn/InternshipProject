package com.example.InternshipProject.controllers;

import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.repositories.InternRepository;
import com.example.InternshipProject.services.abstracts.InternService;
import com.example.InternshipProject.services.dtos.requests.CreateInternRequest;
import com.example.InternshipProject.services.dtos.responses.InternResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interns")

public class InternController {

    private final InternService internService;

    @Autowired
    public InternController(InternService internService){
        this.internService = internService;

    }
    @GetMapping
    public List<Intern> getAll() {
        // Gelen isteği, işi yapması için service katmanına paslıyoruz.
        return this.internService.getAllInterns();
    }

    @GetMapping("/{id}")
    public Intern getInternByID(@PathVariable int id) {
        return internService.getInternByID(id);
    }
    @PostMapping
    public ResponseEntity<Void> addIntern(@RequestBody CreateInternRequest request){
        internService.addIntern(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Intern> deleteInternByID(@PathVariable int id) {
        Intern deletedIntern = internService.deleteInternByID(id);
        return ResponseEntity.ok(deletedIntern);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Intern> updateIntern(@PathVariable int id, @RequestBody Intern internDetails) {
        Intern updatedIntern = internService.updateIntern(id, internDetails);
        return ResponseEntity.ok(updatedIntern);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<InternResponse> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(internService.getByEmail(email));
    }


}