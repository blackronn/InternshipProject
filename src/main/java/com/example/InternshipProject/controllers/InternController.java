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
@CrossOrigin(origins = "http://localhost:8085")
<<<<<<< HEAD
=======

>>>>>>> 049e957 (feat: backend project initial push)
public class InternController {

    private final InternService internService;

    @Autowired
    public InternController(InternService internService){
        this.internService = internService;

    }
    @GetMapping
    public List<Intern> getAllInterns() {
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

    @GetMapping("/exists")
    @ResponseBody
    public ResponseEntity<Boolean> existsByEmail(@RequestParam String email) {
        boolean exists = internService.existsByEmail(email);
        return ResponseEntity.ok(exists);

    }

<<<<<<< HEAD
    @GetMapping("/mentor/{mentorId}")
=======
    @GetMapping("/{mentorId}/interns")
>>>>>>> 049e957 (feat: backend project initial push)
    public ResponseEntity<List<InternResponse>> getInternsByMentorId(@PathVariable Integer mentorId) {
        List<InternResponse> interns = internService.findInternsByMentorId(mentorId);
        return ResponseEntity.ok(interns);
    }

    @GetMapping("/by-email") // YENİ HALİ
    public ResponseEntity<InternResponse> getByEmailAndSyncOffice(
            @RequestParam String email,
            @RequestParam(required = false) String officeLocation) { // officeLocation'ı da alıyoruz
        return ResponseEntity.ok(internService.getByEmailAndSyncOffice(email, officeLocation));
    }

}