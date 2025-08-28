package com.example.InternshipProject.controllers;

import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.services.abstracts.MentorService;
import com.example.InternshipProject.services.dtos.requests.CreateMentorRequest;
import com.example.InternshipProject.services.dtos.responses.MentorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentors")
@CrossOrigin(origins = "http://localhost:8085")
public class MentorController {
    private final MentorService mentorService;

    @Autowired
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public List<Mentor> getAllMentors() {
        return this.mentorService.getAllMentors();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<MentorResponse> getMentorByEmail(@PathVariable String email) {
        return ResponseEntity.ok(mentorService.getMentorByEmail(email));
    }

    @GetMapping("/{id}")
    public Mentor getMentorById(@PathVariable int id) {
        return mentorService.getMentorById(id);
    }

    @PostMapping
    public ResponseEntity<Void> addMentor(@RequestBody CreateMentorRequest request) {
        mentorService.addMentor(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mentor> deleteMentorById(@PathVariable int id) {
        Mentor deletedMentor = mentorService.deleteMentorById(id);
        return ResponseEntity.ok(deletedMentor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mentor> updateMentor(@PathVariable int id, @RequestBody CreateMentorRequest request) {
        Mentor updatedMentor = mentorService.updateMentorById(id, request);
        return ResponseEntity.ok(updatedMentor);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam String email) {
        boolean exists = mentorService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

}
