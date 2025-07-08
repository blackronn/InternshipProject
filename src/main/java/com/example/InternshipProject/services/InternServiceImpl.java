package com.example.InternshipProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.repositories.InternRepository;

import java.util.List;

@Service
public class InternServiceImpl implements InternService{

    private final InternRepository internRepository;

    public InternServiceImpl(InternRepository internRepository) {
        this.internRepository = internRepository;
    }

    @Override
    public Intern getInternByID(int id) {
        return internRepository.findById(id).orElse(null);
    }


}
