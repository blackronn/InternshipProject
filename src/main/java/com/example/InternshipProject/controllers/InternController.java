package com.example.InternshipProject.controllers;

import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.services.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/interns")
public class InternController {

    private final InternService internService;

    @Autowired
    public InternController(InternService internService){
        this.internService = internService;

    }
    @GetMapping("/{id}")
    public Intern getInternByID(@PathVariable int id) {
        return internService.getInternByID(id);
    }


}
