package com.example.InternshipProject.controllers;

import com.example.InternshipProject.entities.concretes.Note;
import com.example.InternshipProject.repositories.NoteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {
    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/{email}")
    public List<Note> getNotes(@PathVariable String email) {
        return noteRepository.findByInternEmail(email);
    }

    @PostMapping
    public Note addNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
    }
}
