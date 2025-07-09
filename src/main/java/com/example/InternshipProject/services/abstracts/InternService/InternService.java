package com.example.InternshipProject.services.abstracts.InternService;

import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.services.dtos.requests.InternRequest.CreateInternRequest;


public interface InternService {

    Intern getInternByID(int id);
    void addIntern(CreateInternRequest request);
    Intern deleteInternByID(int id);

}
