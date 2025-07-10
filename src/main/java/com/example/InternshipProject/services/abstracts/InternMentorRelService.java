package com.example.InternshipProject.services.abstracts;

import com.example.InternshipProject.services.dtos.requests.CreateRelRequest;
import com.example.InternshipProject.services.dtos.requests.UpdateRelRequest;
import com.example.InternshipProject.services.dtos.responses.RelResponse;

import java.util.List;

public interface InternMentorRelService {

    void assignInternToMentor(CreateRelRequest createInternMentorRelRequest);
    List<RelResponse> getAllRelations();
    void updateRelation(UpdateRelRequest request);
    void deleteRelation(int id);
}
