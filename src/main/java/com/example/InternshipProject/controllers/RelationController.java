package com.example.InternshipProject.controllers;

import com.example.InternshipProject.services.abstracts.InternMentorRelService;
import com.example.InternshipProject.services.dtos.requests.CreateRelRequest;
import com.example.InternshipProject.services.dtos.requests.UpdateRelRequest;
import com.example.InternshipProject.services.dtos.responses.RelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relations")
public class RelationController {

    private final InternMentorRelService relService;

    @Autowired
    public RelationController(InternMentorRelService relService){
        this.relService = relService;
    }

    @PostMapping
    public ResponseEntity<Void> assignInternToMentor(@RequestBody CreateRelRequest relRequest) {
        relService.assignInternToMentor(relRequest);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public List<RelResponse> getAllRelations() {
        return relService.getAllRelations();
    }

    @PutMapping
    public void updateRelation(@RequestBody UpdateRelRequest request) {
        relService.updateRelation(request);
    }

    @DeleteMapping("/{id}")
    public void deleteRelation(@PathVariable int id) {
        relService.deleteRelation(id);
    }
}
