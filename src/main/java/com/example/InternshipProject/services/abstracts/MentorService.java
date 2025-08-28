package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.services.dtos.requests.CreateMentorRequest;
import com.example.InternshipProject.services.dtos.responses.MentorResponse;

import java.util.List;

public interface MentorService {
    Mentor getMentorById(int id);
    void addMentor(CreateMentorRequest request);
    Mentor deleteMentorById(int id);
    Mentor updateMentorById(int id, CreateMentorRequest request);
    List<Mentor> getAllMentors();


    boolean existsByEmail(String email);
    MentorResponse getMentorByEmail(String email);


}
