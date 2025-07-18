package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.services.dtos.requests.CreateInternRequest;
import com.example.InternshipProject.services.dtos.responses.InternResponse;

import java.util.List;


public interface InternService {

    Intern getInternByID(int id);
    void addIntern(CreateInternRequest request);
    Intern deleteInternByID(int id);
    Intern updateIntern(int id, Intern internDetails);
    List<Intern> getAllInterns();
    InternResponse getByEmail(String email);
    boolean existsByEmail(String email);



}
