package com.example.InternshipProject.services;

import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.repositories.InternRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public interface InternService {

    Intern getInternByID(int id);
    Intern deleteInternByID(int id);
}
