package com.example.InternshipProject.services;

import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.repositories.InternRepository;
import com.example.InternshipProject.services.dtos.requests.CreateInternRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public interface InternService {

    Intern getInternByID(int id);
    void addIntern(CreateInternRequest request);

}
