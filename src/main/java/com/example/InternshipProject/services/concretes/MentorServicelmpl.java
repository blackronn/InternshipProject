package com.example.InternshipProject.services.concretes;

import com.example.InternshipProject.entities.concretes.Mentor;
import com.example.InternshipProject.repositories.MentorRepository;
import com.example.InternshipProject.services.abstracts.MentorService;
import com.example.InternshipProject.services.dtos.requests.CreateMentorRequest;
import com.example.InternshipProject.services.dtos.responses.MentorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServicelmpl implements MentorService {
    private final MentorRepository mentorRepository;

    public MentorServicelmpl(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;}

        @Override
        public Mentor getMentorById (int id){
            return mentorRepository.findById(id).orElse(null);
        }
        @Override
        public void addMentor (CreateMentorRequest request){
        Mentor mentor=new Mentor();
        mentor.setName(request.getName());
        mentor.setSurname(request.getSurname());
        mentor.setEmail(request.getEmail());
        mentor.setPhoneNumber(request.getPhoneNumber());
        mentor.setPassword(request.getPassword());
        mentor.setTitle(request.getTitle());
        mentor.setIsActive(1);

        mentorRepository.save(mentor);

        }
        @Override
        public Mentor deleteMentorById(int id){
        Mentor mentor=getMentorById(id);
        mentorRepository.deleteById(id);
        return mentor;
        }
        @Override
        public List<Mentor> getAllMentors(){
        return this.mentorRepository.findAll();
        }
        @Override
        public Mentor updateMentorById(int id, CreateMentorRequest request) {
        Mentor mentor = getMentorById(id);

        mentor.setName(request.getName());
        mentor.setSurname(request.getSurname());
        mentor.setEmail(request.getEmail());
        mentor.setPhoneNumber(request.getPhoneNumber());
        mentor.setPassword(request.getPassword());
        mentor.setTitle(request.getTitle());

        return mentorRepository.save(mentor);
    }
    @Override
    public MentorResponse getMentorByEmail(String email) {
        Mentor mentor = mentorRepository.findMentorByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Mentor not found with email: " + email));


        MentorResponse response = new MentorResponse();
        response.setName(mentor.getName());
        response.setSurname(mentor.getSurname());
        response.setEmail(mentor.getEmail());
        response.setId(mentor.getId());
        response.setGender(mentor.getGender());

        return response;
    }

    @Override
    public boolean existsByEmail(String email) {
        return mentorRepository.existsByEmail(email);
    }


}
