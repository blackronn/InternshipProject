package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Intern;
import com.example.InternshipProject.entities.concretes.InternMentorRelation;
import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.repositories.InternMentorRelRepository;
import com.example.InternshipProject.repositories.InternRepository;
import com.example.InternshipProject.repositories.MentorRepository;
import com.example.InternshipProject.services.abstracts.InternMentorRelService;
import com.example.InternshipProject.services.dtos.requests.CreateRelRequest;
import com.example.InternshipProject.services.dtos.requests.UpdateRelRequest;
import com.example.InternshipProject.services.dtos.responses.RelResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelServiceImpl implements InternMentorRelService {

    private final InternMentorRelRepository relRepository;
    private final InternRepository internRepository;
    private final MentorRepository mentorRepository;

    public RelServiceImpl(InternMentorRelRepository relationRepository,
                                      InternRepository internRepository, MentorRepository mentorRepository) {
        this.relRepository = relationRepository;
        this.internRepository = internRepository;
        this.mentorRepository = mentorRepository;
    }


    @Override
    public void assignInternToMentor(CreateRelRequest request) {
        Intern intern = internRepository.findById(request.getInternId())
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        Mentor mentor = mentorRepository.findById(request.getMentorId())
                .orElseThrow(()-> new RuntimeException("Mentor Not Found"));

        InternMentorRelation relation = new InternMentorRelation();
        relation.setIsActive(1);
        relation.setIntern(intern);
        relation.setMentor(mentor);
        relation.setRelationStartDate(relation.getRelationStartDate());
        relation.setRelationEndDate(relation.getRelationEndDate());

        relRepository.save(relation);
    }

    @Override
    public List<RelResponse> getAllRelations() {
        List<InternMentorRelation> relations = relRepository.findAll();

        return relations.stream().map(relation -> {
            RelResponse dto = new RelResponse();
            dto.setRelationId(relation.getRelationId());
            dto.setRelationId(relation.getRelationId());
            dto.setInternId(relation.getIntern().getId());
            dto.setMentorId(relation.getMentor().getId());
            dto.setInternName(relation.getIntern().getName());
            dto.setMentorName(relation.getMentor().getName());
            dto.setCreatedAt(relation.getCreatedAt());
            dto.setIsActive(relation.getIsActive());
            return dto;
        }).toList();
    }



    @Override
    public void updateRelation(UpdateRelRequest request) {
        Intern intern = internRepository.findById(request.getInternId())
                .orElseThrow(() -> new RuntimeException("Intern not found"));
        Mentor mentor = mentorRepository.findById(request.getMentorId())
                .orElseThrow(() -> new RuntimeException("Mentor not found"));
        InternMentorRelation relation = relRepository.findById(request.getRelationId())
                .orElseThrow(() -> new RuntimeException("Relation not found"));

        relation.setIsActive(request.getIsActive());  // <-- DİKKAT!
        relation.setIntern(intern);
        relation.setMentor(mentor);
        // (varsa diğer alanlar...)

        relRepository.save(relation);
    }


    @Override
    public void deleteRelation(int id) {
        InternMentorRelation relation = relRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relation not found"));

        relRepository.delete(relation);
    }
}
